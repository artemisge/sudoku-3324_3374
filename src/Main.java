public class Main {
    public static void main(String[] args)
    {
        Integer test[][] = new Integer[9][9];
        for (int i = 0; i<9; i++){
            for (int j = 0 ; j<9; j++){
                test[i][j] = 1;
            }
        }
        //GUI myGui=new GUI(0, test);
        KillerSudoku ks = new KillerSudoku(test);
    }
}
