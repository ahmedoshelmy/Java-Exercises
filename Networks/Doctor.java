public class Doctor {
    int timeslots_count = 12;
    int id ;
    String name ;
    boolean []timeslots;
    String [] patients ;
    Doctor(){
        patients = new String[timeslots_count];
        timeslots = new boolean[timeslots_count];
        for (int i = 0 ; i<timeslots_count;i++){
            patients[i] = "--------" ;
        }
    }

}
