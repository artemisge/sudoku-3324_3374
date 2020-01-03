import javafx.scene.paint.Color;

import java.io.*;
import java.util.Scanner;

public class KillerSudoku extends SudokuPuzzle {

    protected int[][] regionIndex = new int[getDimension()][getDimension()];//kathe cell, se poia perioxi anikei.
    // ...px to 1o cell anikei stin perioxi 0
    protected int regionNum; //to plhthos ton perioxon pou uparxoun
    protected int[] regionSum; //pinakas me to sum pou exei h kathe perioxi
    protected int[] regionColor; //to xroma tis kathe perioxis se arithmos (endeiktika 1 yellow, 2 green, 3 blue, 4 red)

    public KillerSudoku(int numberOfGame) {
        super(9);
        System.out.println("Killer Sudoku constructor");
        loadFromFile(numberOfGame);

    }


    public boolean isSumOk(int row, int col, int value){
        boolean ok = false;
        int cellsFilled = 0; //filled number of cells in the same region
        int totalCells = 0; //total number of cells in the same region
        int region = regionIndex[row][col]; //in which region the cell is
        int sum = 0; //sum of all cells in the same region up to now
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                if (regionIndex[i][j] == region){
                    sum += grid[i][j];
                    totalCells++;
                    if (grid[i][j] != 0){
                        cellsFilled++;
                    }
                }
            }
        }
        if ((sum + value == regionSum[region]) && (cellsFilled + 1 == totalCells)
                || (sum + value < regionSum[region]) && (cellsFilled + 1 < totalCells)){
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


    private boolean isAllSumOk(){ //to check if game is finished
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
    public void loadFromFile(int numberOfGame) {
        current = numberOfGame;
        try{
            FileReader fileReader = new FileReader("Killer.txt");
            Scanner sc = new Scanner(fileReader);
            int numberOfTotalPuzzlesInFile = sc.nextInt();
            for (int p = 0; p < numberOfTotalPuzzlesInFile; p++) {
                regionNum = 0; //ksekina apo 0 kai kathe fora pou briskei kai alli perioxi auxanetai
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        int tmp = sc.nextInt();
                        if (numberOfGame == p) {
                            regionIndex[i][j] = tmp;
                            //System.out.print(regionIndex[i][j] + "\t");
                        }
                        if (tmp > regionNum) {
                            regionNum = tmp;
                        }
                    }
//                    if (numberOfGame == p) {
//                        System.out.println();
//                    }
                }
                regionNum++;
                regionColor = new int[regionNum];
                regionSum = new int[regionNum];
                for (int i = 0; i < regionNum; i++) {
                    regionColor[i] = sc.nextInt();
                    regionSum[i] = sc.nextInt();
                }
                // Stop reading when puzzle is found :)
                if (numberOfGame == p) {
                    break;
                }
            }
            sc.close();
        }catch(Exception e){
            System.out.println("It was a File error in Killer");
        }//TODO finally

    }

    @Override
    public boolean isSolved() {
        return super.isSolved() && isAllSumOk();
    }

}
