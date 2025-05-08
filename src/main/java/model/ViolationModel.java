package model;

public class ViolationModel {


    String id;


    String studentName;

    String dateSubmitted;

    String violationType;

    String description;

    public  ViolationModel(String id, String studentName, String dateSubmitted, String violationType, String description) {
        this.id = id;
        this.studentName = studentName;
        this.dateSubmitted = dateSubmitted;
        this.violationType = violationType;
        this.description = description;
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

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ViolationModel{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", dateSubmitted='" + dateSubmitted + '\'' +
                ", violationType='" + violationType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
