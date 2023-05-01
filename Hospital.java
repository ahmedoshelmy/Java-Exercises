import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Hospital {

    Doctor [] Doctors;
    int timeslots = 12 ;
    int doctors_count ;
    Hospital(){
        Doctors = new Doctor[100];
        try {
            doctors_count =  readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private  int readFile() throws IOException {
        Scanner scanner = new Scanner(new File("/home/ahmedosama/IdeaProjects/SocketProgramming/src/doctors.txt"));
        int i = 0 ;
        while (scanner.hasNext()) {
            Doctors[i]= new Doctor() ;
            Doctors[i].id = scanner.nextInt();
            Doctors[i++].name = String.valueOf(scanner.next());
        }
        return i;
    }
    boolean makeAppointment(int doc_id , int idx, String patient){
        doc_id = bringIdx(doc_id);
        if(Doctors[doc_id].timeslots[idx]){
            System.out.println("Already Occupied !");
            return  false;
        }else {
            Doctors[doc_id].timeslots[idx] =true;
            Doctors[doc_id].patients[idx] =patient;
            return true;
        }
    }
    void cancelAppointment(int doc_id , int idx, String patient){
        doc_id = bringIdx(doc_id);
        Doctors[doc_id].timeslots[idx] =false;
        Doctors[doc_id].patients[idx] ="No one";
    }

    void PrintAllDocs(){
        for (int i = 0 ; i<doctors_count;i++){
            PrintDoc(i);
        }
    }
    void PrintDoc(int doc_id){
        // Prints  the id of the doctor and the name of his patient in each time slot index
        System.out.println("Doctor ID: " + Doctors[doc_id].id + " Name: " + Doctors[doc_id].name);
        for (int i = 0 ; i<timeslots;i++){
            System.out.print(Doctors[doc_id].patients[i] +" ");
        }
        System.out.println();
    }
    int bringIdx(int doc_id){
        int idx = -1;
        for (int i = 0 ; i<doctors_count;i++){
            if(Doctors[i].id == doc_id){
                return i ;
            }
        }
        return idx;
    }
    boolean IsThatPatientThere(int doc_id ,  int t , String patient_name){
        doc_id = bringIdx(doc_id) ;
        if(Doctors[doc_id].patients[t] == patient_name) return true;
        else return false;
    }

}
