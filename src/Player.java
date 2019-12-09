public class Player {

    private String name;
    private boolean[] puzzlesSolved;


    public Player(String name) {
        this.name = name;
        for (int i = 0; i < Main.PUZZLES; i++){
            puzzlesSolved[i] = false;
        }
    }

    public String getName(){
        return name;
    }

    public boolean hasDonePuzzle(int index){
        return puzzlesSolved[index];
    }

    public void setPuzzleDone(int index){
        puzzlesSolved[index] = true;
    }
}
