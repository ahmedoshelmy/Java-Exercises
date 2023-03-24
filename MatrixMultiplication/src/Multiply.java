public class Multiply implements Runnable {
    public  int [][]numbers;
    private Matrix M1 ;
    private Matrix M2 ;
    private Matrix R ;
    private int M ;
    private int N ;
    private int Z ;

    Multiply(Matrix A , Matrix B , Matrix C){
        M1 = A  ;
        M2 = B ;
        R = C ;
        if(M1.M != M2.N || R.N != M1.N || R.M != M2.M){
            throw new IllegalArgumentException("Please Enter valid Matrices: \n A (nxm) x b(mxz) = C(nxz) \n");
        }
        M = M1.M ; N = M2.N ; Z = M2.M ;
    }
    @Override
    public void run() {
        String cur_thread =Thread.currentThread().getName() ;
        if (cur_thread.equals("1")){
            for (int i = 0 ; i< N;i++){
                for(int j = 0;  j< Z/2;j++){
                    R.numbers[i][j] = 0;
                    for(int k = 0 ; k < M;k++){
                        R.numbers[i][j]+= (M1.numbers[i][k]*M2.numbers[k][j]) ;
                    }
                }
            }

        }else if(cur_thread.equals("2")){
            for (int i = 0 ; i< N;i++){
                for(int j = Z/2;  j< Z;j++){
                    R.numbers[i][j] = 0;
                    for(int k = 0 ; k < M;k++){
                        R.numbers[i][j]+= (M1.numbers[i][k]*M2.numbers[k][j]) ;
                    }
                }
            }
        }
        printMatrixSync();
    }
    synchronized void printMatrixSync(){
        String cur_thread =Thread.currentThread().getName() ;
        System.out.println("Thread "+ cur_thread + " is printing now");
        if (cur_thread.equals("1")){
            for (int i = 0 ; i< N;i++){
                for(int j = 0;  j< Z/2;j++){
                    System.out.print(R.numbers[i][j] + " ");
                }
                System.out.println();
            }

        }else if(cur_thread.equals("2")){
            for (int i = 0 ; i< N;i++){
                for(int j = Z/2;  j< Z;j++){
                    System.out.print(R.numbers[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

}
