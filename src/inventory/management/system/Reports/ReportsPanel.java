package inventory.management.system.Reports;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import inventory.management.system.Reports.ReportsDAO;
import inventory.management.system.Reports.ReportEntity;

public class ReportsPanel extends JPanel {
    private JTable reportTable;
    private DefaultTableModel tableModel;

    public ReportsPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
            new Object[]{"Report Type", "Value"}, 0
        );
        reportTable = new JTable(tableModel);

        loadReports();

        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton refreshBtn = new JButton("Refresh Reports");
        buttonPanel.add(refreshBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshBtn.addActionListener(e -> loadReports());
    }

    private void loadReports() {
        ReportsDAO dao = new ReportsDAO();
        List<ReportEntity> reports = dao.getSummaryReports();

        tableModel.setRowCount(0); 
        for (ReportEntity r : reports) {
            tableModel.addRow(new Object[]{
                r.getReportType(),
                r.getValue()
            });
        }
    }
}
