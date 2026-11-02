package inventory.management.system.Reports;

public class ReportEntity {
    private String reportType;
    private Object value;

    public ReportEntity(String reportType, Object value) {
        this.reportType = reportType;
        this.value = value;
    }

    public String getReportType() { 
        return reportType; 
    }
    public Object getValue() { 
        return value; 
    }
}
