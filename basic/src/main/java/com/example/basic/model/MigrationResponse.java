package com.example.basic.model;

public class MigrationResponse {
    Long totalNumber;
    String status;

    public MigrationResponse(Long totalNumber, String status) {
        this.totalNumber = totalNumber;
        this.status = status;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MigrationResponse{" +
                "totalNumber=" + totalNumber +
                ", status='" + status + '\'' +
                '}';
    }
}
