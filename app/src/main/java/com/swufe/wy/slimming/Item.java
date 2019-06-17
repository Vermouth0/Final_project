package com.swufe.wy.slimming;

public class Item {
    private String studentId;
    private String studentName;

    public Item() {
        this.studentId ="";
        this.studentName = "";
    }

    public Item(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


}
