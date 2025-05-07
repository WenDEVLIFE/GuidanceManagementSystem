package model;

public class StudentModel {

    String id;

    String studentName;

    String Birthdate;

    String guardianName;

    String phoneNumber;

    public  StudentModel(String id, String studentName, String Birthdate, String guardianName, String phoneNumber) {
        this.id = id;
        this.studentName = studentName;
        this.Birthdate = Birthdate;
        this.guardianName = guardianName;
        this.phoneNumber = phoneNumber;
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

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String birthdate) {
        Birthdate = birthdate;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", Birthdate='" + Birthdate + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
