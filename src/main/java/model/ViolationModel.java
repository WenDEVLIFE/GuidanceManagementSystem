package model;

public class ViolationModel {


    String id;


    String studentName;

    String dateSubmitted;

    String violation;

    public  ViolationModel(String id, String studentName, String dateSubmitted, String violation) {
        this.id = id;
        this.studentName = studentName;
        this.dateSubmitted = dateSubmitted;
        this.violation = violation;
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

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    @Override
    public String toString() {
        return "ViolationModel{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", dateSubmitted='" + dateSubmitted + '\'' +
                ", violation='" + violation + '\'' +
                '}';
    }
}
