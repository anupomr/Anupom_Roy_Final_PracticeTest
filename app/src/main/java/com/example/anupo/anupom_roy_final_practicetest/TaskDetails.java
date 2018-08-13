package com.example.anupo.anupom_roy_final_practicetest;

public class TaskDetails {
    private int taskId;
    private String taskLocation, taskCost;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskLocation() {
        return taskLocation;
    }

    public void setTaskLocation(String taskLocation) {
        this.taskLocation = taskLocation;
    }

    public String getTaskCost() {
        return taskCost;
    }

    public void setTaskCost(String taskCost) {
        this.taskCost = taskCost;
    }

    public TaskDetails() {
    }

    public TaskDetails(int taskId, String taskLocation, String taskCost) {
        this.taskId = taskId;
        this.taskLocation = taskLocation;
        this.taskCost = taskCost;
    }
}
