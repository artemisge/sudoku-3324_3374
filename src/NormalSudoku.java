import java.io.FileReader;
import java.util.Scanner;

public class NormalSudoku extends SudokuPuzzle {


    public NormalSudoku(Integer[][] fileGrid) {
        super(9, fileGrid);
        System.out.println("Normal Sudoku contstructor");
        loadFromFile();
    }


    @Override
    public void loadFromFile(){
        try{

            FileReader fileReader = new FileReader("C:\\Users\\user\\IdeaProjects\\sudoku-3324_3374\\src\\Normal.txt");
            Scanner sc = new Scanner(fileReader);
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++){
                    int tmp = sc.nextInt();
                    grid[i][j] = tmp;
                }
            }
            sc.close();
        }catch(Exception e){
            System.out.println("It was a File Oopsie in Classic");
        }//TODO finally

    }
}
