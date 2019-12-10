public class Player {

    private String name;
    private boolean[][] puzzlesSolved= new boolean[2][10]; //i=0 classic, i=1 killer


    public Player(String name, int gameType) {
        this.name = name;
        for (int i = 0; i < Main.PUZZLES; i++){
            puzzlesSolved[gameType][i] = false;
        }
    }

    public String getName(){
        return name;
    }

    public boolean hasDonePuzzle(int index, int gameType){
        return puzzlesSolved[gameType][index];
    }

    public void setPuzzleDone(int index, int gameType){
        puzzlesSolved[gameType][index] = true;
    }
}
