import javafx.scene.paint.Color;

import java.io.*;
import java.util.Scanner;

public class KillerSudoku extends SudokuPuzzle {

    protected int[][] regionIndex = new int[dimension][dimension];//kathe cell, se poia perioxi anikei.
    // ...px to 1o cell anikei stin perioxi 0
    protected int regionNum; //to plhthos ton perioxon pou uparxoun
    protected int[] regionSum; //pinakas me to sum pou exei h kathe perioxi
    protected int[] regionColor; //to xroma tis kathe perioxis se arithmos (endeiktika 1 yellow, 2 green, 3 blue, 4 red)

    public KillerSudoku(int numberOfGame) {
        super(9);
        System.out.println("Killer Sudoku constructor");
        loadFromFile(numberOfGame);

    }


    /**
     * taken the region of the cell the user wants to insert the value, it calculates the sum than
     * needs to be in that region and compares it with the current sum.
     * cellsFilled : filled number of cells in the same region
     * totalCells : total number of cells in the same region
     * region : in which region the cell is
     * sum : sum of all cells in the same region up to now
     * @param row
     * @param col
     * @param value
     * @return it will return true if the current sum equals the needed sum and the region cells are all filled
     * OR if the sum is less than the needed and the cells of the region aren't filled.
     */
    public boolean isSumOk(int row, int col, int value){
        boolean ok = false;
        int cellsFilled = 0;
        int totalCells = 0;
        int region = regionIndex[row][col];
        int sum = 0;
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
        }
        return ok;
    }

    /**
     * Works the same as the super one but with the addition of checking if the the sum is ok in the specific region.
     * @param row
     * @param col
     * @param value
     * @return
     */
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
                        }
                        if (tmp > regionNum) {
                            regionNum = tmp;
                        }
                    }
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

    /**
     * checks if tha game is finished by checking if all needed sums in regions are ok.
     * @return
     */
    @Override
    public boolean isSolved() {
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

}
