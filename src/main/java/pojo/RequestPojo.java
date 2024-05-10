package pojo;

public class RequestPojo {
    private String sno;
    private String title;
    private String description;
    private String raisedBy;
    private String requestDate;
    private String department;
    private String approvalDate;
    private String approvedBy;
    private String status;

    public RequestPojo() {
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(String raisedBy) {
        this.raisedBy = raisedBy;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RequestPojo{" +
                "sno='" + sno + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", raisedBy='" + raisedBy + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", department='" + department + '\'' +
                ", approvalDate='" + approvalDate + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
