import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException{

        Scanner in = new Scanner(System.in);
        int N1, M1, N2, M2;
        System.out.println("Enter the dimensions of the first Matrix");
        N1 = in.nextInt();
        M1 = in.nextInt();
        Matrix A = new Matrix(N1, M1);
        System.out.println("Enter the elements of the first Matrix row by row");
        for (int i = 0; i < N1; i++) {
            for (int j = 0; j < M1; j++) {
                A.numbers[i][j] = in.nextInt();
            }
        }
        System.out.println("Enter the dimensions of the second Matrix");
        N2 = in.nextInt();
        M2 = in.nextInt();
        Matrix B = new Matrix(N2, M2);
        System.out.println("Enter the elements of the second Matrix row by row");
        for (int i = 0; i < N2; i++) {
            for (int j = 0; j < M2; j++) {
                B.numbers[i][j] = in.nextInt();
            }
        }
        Matrix C = new Matrix(N1 , M2) ;
        Multiply MP = new Multiply(A , B , C) ;
        Thread t1 = new Thread (MP);
        t1.setName("1");
        Thread t2 = new Thread (MP);
        t2.setName("2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Matrix C: ");
        C.Print();
    }
}