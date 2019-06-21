package com.swufe.wy.slimming;

public class PlanItem {

    private int id;
    private String planTitle;
    private String planContent;
    private int finishTimes;

    public PlanItem() {
        super();
        planTitle = "";
        planContent = "";
        finishTimes = 0;
    }

    public PlanItem(String planTitle, String planContent, int finishTimes) {
        super();
        this.planTitle = planTitle;
        this.planContent = planContent;
        this.finishTimes = finishTimes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanTitle() {
        return planTitle;
    }
    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String planContent() {
        return planContent;
    }
    public void planContent(String planContent) {
        this.planContent = planContent;
    }

    public int getFinishTimes() {
        return finishTimes;
    }

    public void setFinishTimes(int finishTimes) {
        this.finishTimes = finishTimes;
    }
}