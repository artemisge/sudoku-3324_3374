public class Main {
    public static void main(String[] args)
    {
        Integer test[][] = new Integer[9][9];
        for (int i = 0; i<9; i++){
            for (int j = 0 ; j<9; j++){
                test[i][j] = 1;
            }
        }
        Integer test2[][] = new Integer[4][4];
        for (int i = 0; i<4; i++){
            for (int j = 0 ; j<4; j++){
                test2[i][j] = 5;
            }
        }
        GUI myGui=new GUI(2, test2);
        //KillerSudoku ks = new KillerSudoku(test);
    }
}
