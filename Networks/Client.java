import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static final int  CANCEL_PORT = 6666;
    public static final int MAKE_PORT = 6667;

    public static void main(String[] args) throws UnknownHostException, IOException {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello Patient .. Enter your name");
        String patient_name = scanner.next();
        Socket cancel_socket = new Socket("localhost", CANCEL_PORT);
        Socket make_socket = new Socket("localhost", MAKE_PORT);
        PrintWriter cancel_socket_out = new PrintWriter(cancel_socket.getOutputStream(), true);
        PrintWriter  make_socket_out = new PrintWriter(make_socket.getOutputStream(), true);
        cancel_socket_out.println(patient_name);
        make_socket_out.println(patient_name);
        BufferedReader cancelSocketReader = new BufferedReader(new InputStreamReader(cancel_socket.getInputStream()));
        BufferedReader makeSocketReader = new BufferedReader(new InputStreamReader(make_socket.getInputStream()));

        while (true){
            System.out.println("Enter the service you want to connect to: (1) CANCEL_PORT (2) MAKE_PORT (3) End Client");
            int service = scanner.nextInt() ;
            if(service == 3) {
                cancel_socket_out.println(-1);
                make_socket_out.println(-1);
                break;
            }
            System.out.println("Enter the id of the doctor");
            int doc_id = scanner.nextInt();
            System.out.println("Enter the timeslot index 0-->>11 of the doctor");
            int timeslot_index = scanner.nextInt();
            if(service == 1){
                cancel_socket_out.println(doc_id) ;
                cancel_socket_out.println(timeslot_index);
                System.out.println("The server sent the following message: " + cancelSocketReader.readLine());
            }else if(service ==2) {
                make_socket_out.println(doc_id) ;
                make_socket_out.println(timeslot_index);
                System.out.println("The server sent the following message: " + makeSocketReader.readLine());
            }
        }
        System.out.println("I am exiting now, bye.");
        scanner.close();
        cancel_socket_out.close();
        make_socket_out.close();;
        cancelSocketReader.close();
        makeSocketReader.close();

    }
}
