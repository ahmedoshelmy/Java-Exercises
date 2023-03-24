public class Matrix {
    public  int M ;
    public  int N ;
    public  int [][]numbers;
    Matrix(int rows , int cols){
        M = cols ; N = rows;
        numbers = new int[N][M];
    }
    boolean SetNumbers( int [] arr){
        int n = arr.length;
        if(n  != N*M) {
            System.out.print("Please Enter valid data , It's size should be " );
            System.out.println(M*N);
            return false;
        }
        for (int i = 0; i< N;i++){
            for (int j = 0; j<M;j++)
                numbers[i][j] = arr[i*M+j];

        }
        return true;
    }
    void Print(){
        for (int i = 0; i< N;i++){
            for (int j = 0; j<M;j++){
                System.out.printf( "%d ",numbers[i][j]);
            }
            System.out.println();
        }
    }
    void Transpose(){
        int [][] transpose = new int[M][N];
        for (int i = 0; i< N;i++){
            for (int j = 0; j<M;j++){
                transpose[j][i] = numbers[i][j] ;
            }
        }
        numbers = transpose ;
        // Swapping the rows and the columns
        M = M ^ N;
        N = M ^ N;
        M = M ^ N;
    }

    public Object Add(Object obj2) {
        Matrix X = (Matrix) obj2;
        if(X.N != this.N || X.M != this.M) { // They should have the same dimensions
            System.out.println("They should be the same dimensions");
            return this;
        }
        for (int i = 0 ; i< N; i++)
            for (int j = 0; j<M; j++)
                numbers[i][j] += X.numbers[i][j];
        return this;
    }
}
