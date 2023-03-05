import java.util.*;

public class Main {

    public interface Addable {
        public Object Add (Object obj2) ;
    }
    public static class Matrix implements Addable {
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
    public static class IdentityMatrix extends Matrix{

        IdentityMatrix(int rows, int cols) {
            super(rows, cols);
        }
        @Override
        public boolean SetNumbers( int [] arr){
            int n = arr.length;
            if(n  != N*M) {
                System.out.print("Please Enter valid data , It should be " );
                System.out.println(M*N);
                return false;
            }
            for (int i = 0; i< N;i++){
                for (int j = 0; j<M;j++){
                    if(i == j && arr[i*M+j]!=1) return false;
                    if(i != j && arr[i*M+j]!=0) return false;
                    numbers[i][j] = arr[i*N+j];
                }
            }
            return true;
        }
        @Override
        void Transpose(){
            System.out.println("The transpose of an identity matrix is itself");
        }
    }

    public static void main(String[] args) {

        Matrix Mx = new Matrix(5 , 2) ;
        int [] arr ={1 , 3, 5 } ;
        // Setting --------------------------
        Mx.SetNumbers(arr) ; // Invalid
        arr = new int[]{1, 2, 3, 4, 5 , 6 , 7 , 8 , 9 , 10};
        Mx.SetNumbers(arr); // Valid
        // Printing --------------------------
        Mx.Print();
        //Transpose
        System.out.println("After Transpose : ");
        Mx.Transpose();
        Mx.Print();
        // Adding two Matrices ;
        Matrix Mx2 = new Matrix(2 , 5) ;
        Mx2.SetNumbers(arr) ;
        Mx2.Add(Mx);
        System.out.println("After Adding : ");
        Mx2.Print();
        // Identity Matrix
        IdentityMatrix Mx3 = new IdentityMatrix(3,3);
        Mx3.SetNumbers(arr) ; // Invalid
        arr = new int[]{1, 0, 0, 0, 0 , 0 , 0 , 0 , 0 };
        Mx3.SetNumbers(arr); // Invalid as well
        arr = new int[]{1, 0, 0, 0, 1 , 0 , 0 , 0 , 1};
        Mx3.SetNumbers(arr);
        Mx3.Print(); ;
        Mx3.Transpose();
    }
}
