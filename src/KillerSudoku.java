import javafx.scene.paint.Color;

import java.io.*;
import java.util.Scanner;

public class KillerSudoku extends SudokuPuzzle {

    private int[][] regionIndex = new int[getDimension()][getDimension()];//kathe cell, se poia perioxi anikei.
    // ...px to 1o cell anikei stin perioxi 0
    private int regionNum; //to plhthos ton perioxon pou uparxoun
    private int[] regionSum; //pinakas me to sum pou exei h kathe perioxi
    private int[] regionColor; //to xroma tis kathe perioxis se arithmos (endeiktika 1 yellow, 2 green, 3 blue, 4 red)

    public KillerSudoku(Integer[][] fileGrid) {
        super(9, fileGrid);

        //this code will be on IOManager Class, but it's here temporarily for testing
        try{
//            String fileName = "src/Killer1.txt";
//            FileInputStream fileInputStream = new FileInputStream(fileName);
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//            try(BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
//                int number;
//                while((number = Integer.parseInt(bufferedReader.readLine())) != -1){
//                    System.out.println(number);
//                }
//            }
            FileReader fileReader = new FileReader("C:\\Users\\Oxi reman KALA\\IdeaProjects\\sudoku-3324_3374\\src\\Killer1.txt");
            Scanner sc = new Scanner(fileReader);
            regionNum = 0;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++){
                    int tmp = sc.nextInt();
                    regionIndex[i][j] = tmp;
                    System.out.println(regionIndex[i][j]);
                    if (tmp > regionNum){
                        regionNum = tmp;
                    }
                }
            }
            regionNum++;
            regionColor = new int[regionNum];
            regionSum = new int[regionNum];
            for (int i = 0; i < regionNum; i++){
                regionColor[i] = sc.nextInt();
                regionSum[i] = sc.nextInt();
            }
            sc.close();
        }catch(Exception e){
            System.out.println("It was a File Oopsie");
        }//TODO finally


    }


    public boolean isSumOk(int row, int col, int value){
        boolean ok = false;
        int cellsFilled = 0;
        int cells = 0;
        int region = regionIndex[row][col];
        int sum = 0;
        for (int i = 0; i < regionNum; i++){
            for (int j = 0; j < regionNum; j++){
                if (regionIndex[i][j] == region){
                    sum += grid[i][j];
                    cells++;
                    if (grid[i][j] != 0){
                        cellsFilled++;
                    }
                }
            }
        }
        if ((sum + value == regionSum[region]) && (cellsFilled + 1 == cells)
                || (sum + value < regionSum[region]) && (cellsFilled + 1 < cells)){
            ok = true;
            //to athroisma einai teleio me ola ta kelia sumpliromena h ta kelia den exoun
            //sumplirothei kai to athroisma tous den kseperna to apaitoumeno athroisma
        }
        return ok;
    }


    public int[] getRegionSum() {
        return regionSum;
    }


    public int getRegionNum(){
        return regionNum;
    }


    private boolean isAllSumOk(){
        for (int i = 0; i < regionNum; i++){
            //gia kathe perioxi
            int sum =0;
            for (int j = 0; j < dimension; j++){
                for (int k = 0; k < dimension; k++){
                    if (regionIndex[j][k] == i){
                        sum += grid[j][k];
                    }
                }

            }
            if (sum != regionSum[i]){
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean canInsert(int row, int col, int value){
        return super.canInsert(row, col, value) && isSumOk(row, col, value);
    }


    @Override
    public boolean isSolved() {
        return super.isSolved() && isAllSumOk();
    }

}
