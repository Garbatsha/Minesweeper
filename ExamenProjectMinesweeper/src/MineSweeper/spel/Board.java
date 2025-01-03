package MineSweeper.spel;

import MineSweeper.model.MusicModel;

import java.util.Random;

public class Board {

    private Random random;
    private Tile clickedBomb;
    private Tile[][] board;
    private boolean Win;
    private boolean Loss;
    private int mineAmount;
    private int flaggedMineAmount;

    //constructor voor het bord
    public Board(int boardWidth, int boardHeight, int mineAmount) {
        this.mineAmount = mineAmount;
        board = new Tile[boardWidth][boardHeight];
        random = new Random();
    }

    //start het spel vanaf een bepaalde vakje
    public void startGame(int startTileX, int startTileY) {
        fillBoard(startTileY, startTileY);
    }

    // Onthul een vakje en eventueel omliggende vakjes
    public void revealTile(int tileX, int tileY, boolean canCascade) {
        //controleer of de coordinaten geldig zijn
        if (!validateCoordinate(tileX, tileY)) {
            return;
        }
        //als het vakje niet verborgen is
        if (!board[tileX][tileY].isHidden()) {
            //als het vakje kan worden scrubbed, onthul omgringde vakje
            if (canScrubTile(tileX, tileY) && canCascade) {
                revealSurroundingTiles(tileX, tileY);
                MusicModel.speelGeluidEffect(MusicModel.GeluidsEffect.CLICK);
            }
            return;
        }

        //het vakje is gemarkeerd met een vlag
        if (board[tileX][tileY].isFlagged()) {
            return;
        }

        //onthul vakje
        board[tileX][tileY].setHidden(false);

        //als het onthullen van het vakje een cascade veroorzaakt
        if (canCascade) {
            MusicModel.speelGeluidEffect(MusicModel.GeluidsEffect.CLICK);
        }

        //als het vakje leef is en geen bom bevat
        if (board[tileX][tileY].getMineAmount() == 0 && !(board[tileX][tileY].isMine())) {
            revealSurroundingTiles(tileX, tileY);
        }

        //check voor win/loss
        if (board[tileX][tileY].isMine()) {
            board[tileX][tileY].startAnimatie();
            clickedBomb = board[tileX][tileY];
            MusicModel.speelGeluidEffect(MusicModel.GeluidsEffect.MIJN);
            Loss = true;
        }

        //controleer of alle mijnen correct zijn gemarkeerd met een vlag
        Win = checkMinesCorrectlyFlagged();
    }

    //methode om de omliggende vakjes te onthullen
    private void revealSurroundingTiles(int tileX, int tileY) {
        revealTile(tileX - 1, tileY - 1, false);
        revealTile(tileX, tileY - 1, false);
        revealTile(tileX + 1, tileY - 1, false);
        revealTile(tileX - 1, tileY, false);
        revealTile(tileX + 1, tileY, false);
        revealTile(tileX - 1, tileY + 1, false);
        revealTile(tileX, tileY + 1, false);
        revealTile(tileX + 1, tileY + 1, false);
    }

    //methode om een vakje te markeren met een vlag
    public void flagTile(int tileX, int tileY) {
        //controleer of de coordinaten geldig zijn
        if (!validateCoordinate(tileX, tileY)) {
            return;
        }

        //als het vakje niet verborgen is
        if (!board[tileX][tileY].isHidden()) {
            //als het vakje al is gemarkeerd, verwijder de vlag
            if (board[tileX][tileY].isFlagged()) {
                board[tileX][tileY].setFlagged(false);
                flaggedMineAmount--;
            }
            return;
        }

        //als het vakje is gemarkeerd, verwijder de vlag, anders voeg een vlag toe
        if (board[tileX][tileY].isFlagged()) {
            board[tileX][tileY].setFlagged(false);
            flaggedMineAmount--;
        } else {
            board[tileX][tileY].setFlagged(true);
            flaggedMineAmount++;
        }

        MusicModel.speelGeluidEffect(MusicModel.GeluidsEffect.VLAG);

        //check voor win
        Win = checkMinesCorrectlyFlagged();
    }

    //methode om het bord te vullen met vakjes en mijnen
    private void fillBoard(int startTileX, int startTileY) {
        boolean[][] mines = new boolean[board.length][board[0].length];
        int placedMines = 0;

        //plaatsen van de mijnen op het bprd
        while (placedMines < mineAmount) {
            int mineX = random.nextInt(board.length);
            int mineY = random.nextInt(board[mineX].length);

            //controleer of de mijn niet op het startvakje wordt geplaatst
            if (!(mines[mineX][mineY]) && !(mineX == startTileX && mineY == startTileY)) {
                mines[mineX][mineY] = true;
                placedMines++;
            }
        }

        //bepaal het aantal omliggende mijnen voor elke vakje
        for (int row = 0; row < board.length; ++row) {
            for (int column = 0; column < board[row].length; ++column) {
                //calculate surrounding mines
                int surroundMines = 0;
                if (tileIsMine(row - 1, column - 1, mines)) surroundMines++;
                if (tileIsMine(row, column - 1, mines)) surroundMines++;
                if (tileIsMine(row + 1, column - 1, mines)) surroundMines++;
                if (tileIsMine(row - 1, column, mines)) surroundMines++;
                if (tileIsMine(row + 1, column, mines)) surroundMines++;
                if (tileIsMine(row - 1, column + 1, mines)) surroundMines++;
                if (tileIsMine(row, column + 1, mines)) surroundMines++;
                if (tileIsMine(row + 1, column + 1, mines)) surroundMines++;

                //maak een nieuw vakje met het verekende aantal mijnen en markeet het als verborgen
                Tile newTile = new Tile(surroundMines, mines[row][column]);
                newTile.setHidden(true);
                board[row][column] = newTile;
            }
        }
    }

    //methode om te controleren of een vakje kan scrubben
    public boolean canScrubTile(int tileX, int tileY) {
        //controleer of de coordinaten geldig zijn
        if (!validateCoordinate(tileX, tileY)) {
            return false;
        }

        //controleer of het vakje is verborgen
        if (board[tileX][tileY].isHidden()) {
            return false;
        }

        int surroundFlags = 0;

        //controleer  het aantal gemarkeerde omliggende vakjes
        if (tileIsFlagged(tileX - 1, tileY - 1)) surroundFlags++;
        if (tileIsFlagged(tileX, tileY - 1)) surroundFlags++;
        if (tileIsFlagged(tileX + 1, tileY - 1)) surroundFlags++;
        if (tileIsFlagged(tileX - 1, tileY)) surroundFlags++;
        if (tileIsFlagged(tileX + 1, tileY)) surroundFlags++;
        if (tileIsFlagged(tileX - 1, tileY + 1)) surroundFlags++;
        if (tileIsFlagged(tileX, tileY + 1)) surroundFlags++;
        if (tileIsFlagged(tileX + 1, tileY + 1)) surroundFlags++;

        return (surroundFlags == board[tileX][tileY].getMineAmount());
    }

    //methode om te controleren of een vakje een mijn bevat
    private boolean tileIsMine(int x, int y, boolean[][] mines) {
        if (!validateCoordinate(x, y)) {
            return false;
        }

        return mines[x][y];
    }

    //methode om te controleren of een vakje gemarkeerd is met een vlag
    private boolean tileIsFlagged(int tileX, int tileY) {
        if (!validateCoordinate(tileX, tileY)) {
            return false;
        }

        return board[tileX][tileY].isFlagged();
    }

    //methode om te controleren of alle mijnen correct zijn gemarkeerd met een vlag
    private boolean checkMinesCorrectlyFlagged() {
        if (flaggedMineAmount != mineAmount) {
            return false;
        } else {
            boolean incorrectFlag = false;
            for (Tile[] tileRow : board) {
                for (Tile currentTile : tileRow) {
                    if (currentTile.isMine() != currentTile.isFlagged()) {
                        incorrectFlag = true;
                    }
                }
            }
            return !incorrectFlag;
        }
    }

    //methode om te controleren of de coordinaten geldig zijn binnen het bord
    public boolean validateCoordinate(int tileX, int tileY) {
        if (tileX < 0 || tileX >= board.length) {
            // x-waarde buiten bereik
            return false;
        }

        if (tileY < 0 || tileY >= board[tileX].length) {
            //y-waarde buiten bereik
            return false;
        }

        return true;
    }

    //getters and setters
    public Tile[][] getBoard() {
        return board;
    }

    public boolean isVictory() {
        return Win;
    }

    public boolean isGameOver() {
        return Loss;
    }

    public void setGameOver(boolean gameOver) {
        this.Loss = gameOver;
    }

    public int getAmountFlagsPlaced() {
        return flaggedMineAmount;
    }

    public boolean isAnimationPlaying() {
        clickedBomb.updateAnimationFrame();
        return clickedBomb.isPlayingAnimation();
    }

    public Tile getClickedBomb() {
        return clickedBomb;
    }
}

    

