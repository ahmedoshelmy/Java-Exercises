import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int  CANCEL_PORT = 6666;
    public static final int MAKE_PORT = 6667;
    static int clientNumber;
    static Hospital myHospital ;
    static int timeslotsMax = 12;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("The server started .. ");
        myHospital = new Hospital() ;
        clientNumber =0 ;
        new Thread() {
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(CANCEL_PORT);
                    while (true) {
                        new RequestHandler(ss.accept(), clientNumber++).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(MAKE_PORT);
                    while (true) {
                        new RequestHandler(ss.accept(), clientNumber++).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private static class RequestHandler extends Thread {
        Socket socket;
        int clientNo;

        public RequestHandler(Socket s, int clientNo) {
            this.socket = s;
            this.clientNo = clientNo;
            System.out.println("Connection with Client #" + clientNo + "at socket " + socket);
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
                String patientName = br.readLine();
                System.out.println("Patient : " + patientName);
                while (true) {
                    int doc_id = Integer.parseInt(br.readLine());
                    if(doc_id == -1) break;
                    int timeslot_index = Integer.parseInt(br.readLine());
                    System.out.println("Doc ID " + doc_id +" timeslot_idx " + timeslot_index);
                    String response = null;
                    if(myHospital.bringIdx(doc_id) == -1) response = "The doctor id is not found in hospital";
                    else if(timeslot_index >= timeslotsMax ) response =" the timeslot index is out of boundary";
                    else {
                        if (this.socket.getLocalPort() == CANCEL_PORT) {
                            if(!myHospital.IsThatPatientThere(doc_id , timeslot_index , patientName))
                                response = "the doctor has an appointment to a different patient name at this\n" +
                                        "timeslot";
                            else {
                                myHospital.cancelAppointment(doc_id, timeslot_index, patientName);
                                response = "Cancelling the appointment is done successfully";
                            }
                        }
                        else {
                            if (myHospital.makeAppointment(doc_id, timeslot_index, patientName)) {
                                response = "Making the appointment is done successfully";
                            } else {
                                response = "The doctor is already busy at this timeslot";
                            }
                        }
                    }
                    out.println(response);
                    myHospital.PrintAllDocs();
                }
                out.close();
                br.close();
                socket.close();
                System.out.println("Connection with Client #" + this.clientNo + " finished..");
            } catch (IOException e) {
                System.out.println("Error handling client# " + this.clientNo + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close a socket, what's going on?");
                }
                System.out.println("Connection with client# " + this.clientNo + " closed");
            }
        }
    }


}
