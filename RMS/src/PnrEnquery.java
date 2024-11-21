
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PnrEnquery extends JFrame {
    private JTextField pnrInputField;

    public PnrEnquery() {
        this.setTitle("PNR Enquiry");
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(2);
        JLabel titleLabel = new JLabel("PNR Enquiry", 0);
        titleLabel.setFont(new Font("Arial", 1, 20));
        this.add(titleLabel, "North");
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel pnrLabel = new JLabel("Enter PNR: ");
        this.pnrInputField = new JTextField(15);
        JButton fetchButton = new JButton("Fetch Details");
        inputPanel.add(pnrLabel);
        inputPanel.add(this.pnrInputField);
        inputPanel.add(fetchButton);
        this.add(inputPanel, "Center");
        fetchButton.addActionListener((e) -> {
            this.fetchDetails();
        });
        this.setVisible(true);
    }

    private void fetchDetails() {
        String pnrInput = this.pnrInputField.getText().trim();
        if (pnrInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "PNR input cannot be empty.", "Error", 0);
        } else {
            try {
                int pnr = Integer.parseInt(pnrInput);
                conn c = new conn();
                String query = "SELECT * FROM passenger WHERE pnr = ?";
                PreparedStatement pstmt = c.c.prepareStatement(query);
                pstmt.setInt(1, pnr);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String railwayCode = rs.getString("railway_code");
                    String source = rs.getString("source");
                    String destination = rs.getString("destination");
                    String date = rs.getString("date");
                    String name = rs.getString("name");
                    String gender = rs.getString("gender");
                    String aadhar = rs.getString("aadhar");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    int amount = rs.getInt("amount");
                    String details = String.format("PNR: %d\nRailway Code: %s\nSource: %s\nDestination: %s\nDate: %s\nName: %s\nGender: %s\nAadhar: %s\nAddress: %s\nPhone: %s\nFare Amount: %d", pnr, railwayCode, source, destination, date, name, gender, aadhar, address, phone, amount);
                    JOptionPane.showMessageDialog(this, details, "PNR Details", 1);
                } else {
                    JOptionPane.showMessageDialog(this, "No details found for PNR: " + pnr, "Error", 0);
                }
            } catch (NumberFormatException var18) {
                JOptionPane.showMessageDialog(this, "Invalid PNR number. Please enter a valid numeric PNR.", "Error", 0);
            } catch (Exception var19) {
                var19.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + var19.getMessage(), "Error", 0);
            }

        }
    }

    public static void main(String[] args) {
        new PnrEnquery();
    }
}
