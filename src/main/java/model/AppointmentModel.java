package model;

public class AppointmentModel {
    String id;

    String studentName;

    String guardianName;

    String appointmentDate;

    String appointmentTime;

    String dateSubmitted;

    public AppointmentModel(String id, String studentName, String guardianName, String appointmentDate, String appointmentTime, String dateSubmitted) {
        this.id = id;
        this.studentName = studentName;
        this.guardianName = guardianName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.dateSubmitted = dateSubmitted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }


    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    @Override
    public String toString() {
        return "AppointmentModel{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", dateSubmitted='" + dateSubmitted + '\'' +
                '}';
    }

}
