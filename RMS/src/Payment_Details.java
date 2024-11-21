
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Payment_Details extends JFrame {
    JTextField textField;
    JTextField textField1;
    JTextField textField2;
    JTable table;
    JLabel Sector;
    JLabel FlightCode;
    JLabel Capacity;
    JLabel Classcode;
    JLabel Classname;
    JLabel label;
    JButton CheckButton;

    public static void main(String[] args) {
        new Payment_Details();
    }

    public Payment_Details() {
        this.initialize();
    }

    private void initialize() {
        this.setTitle("PAYMENT_DETAILS");
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(860, 486);
        this.setLayout((LayoutManager)null);
        JLabel Fcode = new JLabel("PNR NO");
        Fcode.setFont(new Font("Tahoma", 0, 20));
        Fcode.setBounds(60, 90, 150, 30);
        this.add(Fcode);
        this.textField = new JTextField();
        this.textField.setBounds(200, 90, 150, 30);
        this.add(this.textField);
        this.CheckButton = new JButton("Check");
        this.CheckButton.setFont(new Font("Tahoma", 0, 17));
        this.CheckButton.setBackground(Color.BLACK);
        this.CheckButton.setForeground(Color.WHITE);
        this.CheckButton.setBounds(370, 90, 100, 30);
        this.add(this.CheckButton);
        JLabel Amount = new JLabel("Amount");
        Amount.setFont(new Font("Tahoma", 0, 20));
        Amount.setBounds(60, 130, 150, 30);
        this.add(Amount);
        this.textField1 = new JTextField();
        this.textField1.setBounds(200, 130, 150, 30);
        this.textField1.setVisible(false);
        this.add(this.textField1);
        JLabel pin = new JLabel("Name");
        pin.setFont(new Font("Tahoma", 0, 20));
        pin.setBounds(60, 170, 150, 30);
        this.add(pin);
        this.textField2 = new JTextField();
        this.textField2.setBounds(200, 170, 150, 30);
        this.textField2.setVisible(false);
        this.add(this.textField2);
        JButton Show = new JButton("PAY");
        Show.setFont(new Font("Tahoma", 0, 17));
        Show.setBackground(Color.BLACK);
        Show.setForeground(Color.WHITE);
        Show.setBounds(60, 260, 290, 26);
        this.add(Show);
        this.Sector = new JLabel("PAYMENT SECTION");
        this.Sector.setForeground(Color.BLUE);
        this.Sector.setFont(new Font("Tahoma", 0, 31));
        this.Sector.setBounds(51, 17, 300, 39);
        this.add(this.Sector);
        this.label = new JLabel("");
        this.label.setIcon(new ImageIcon(ClassLoader.getSystemResource("icon/payment.png")));
        this.label.setBounds(425, 15, 400, 272);
        this.add(this.label);
        this.setVisible(true);

        try {
            conn c = new conn();
            String createTableQuery = "    CREATE TABLE IF NOT EXISTS payment (\n        pnr INT PRIMARY KEY,\n        name VARCHAR(50),\n        amount INT,\n        status VARCHAR(20)\n    )\n";
            c.s.executeUpdate(createTableQuery);
        } catch (SQLException var7) {
            JOptionPane.showMessageDialog((Component)null, "Error creating payment table: " + var7.getMessage());
        }

        this.CheckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String pnr = Payment_Details.this.textField.getText();
                    conn c = new conn();
                    String query = "SELECT * FROM passenger WHERE pnr=" + pnr;
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()) {
                        String name = rs.getString("name");
                        int amount = rs.getInt("amount");
                        JOptionPane.showMessageDialog((Component)null, "PNR Details Found: \nName: " + name + "\nAmount: " + amount);
                        Payment_Details.this.textField1.setVisible(true);
                        Payment_Details.this.textField2.setVisible(true);
                        Payment_Details.this.textField1.setText(String.valueOf(amount));
                        Payment_Details.this.textField2.setText(String.valueOf(name));
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "No details found for the given PNR.");
                        Payment_Details.this.textField1.setVisible(false);
                        Payment_Details.this.textField2.setVisible(false);
                    }
                } catch (SQLException var8) {
                    JOptionPane.showMessageDialog((Component)null, "Error fetching passenger details: " + var8.getMessage());
                }

            }
        });
        Show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String pnr = Payment_Details.this.textField.getText();
                    String amount = Payment_Details.this.textField1.getText();
                    String pin = Payment_Details.this.textField2.getText();
                    conn c = new conn();
                    String checkPaymentQuery = "SELECT status FROM payment WHERE pnr=" + pnr;
                    ResultSet rsCheck = c.s.executeQuery(checkPaymentQuery);
                    String status;
                    if (rsCheck.next()) {
                        status = rsCheck.getString("status");
                        if ("SUCCESSFUL".equals(status)) {
                            JOptionPane.showMessageDialog((Component)null, "Payment already made. Status: Paid");
                        } else {
                            JOptionPane.showMessageDialog((Component)null, "Payment status is not successful. Please try again.");
                        }
                    } else {
                        status = "SELECT name FROM passenger WHERE pnr=" + pnr;
                        ResultSet rs = c.s.executeQuery(status);
                        if (rs.next()) {
                            String name = rs.getString("name");
                            String insertPaymentQuery = "INSERT INTO payment (pnr, name, amount, status) VALUES (" + pnr + ", '" + name + "', " + amount + ", 'SUCCESSFUL')";
                            c.s.executeUpdate(insertPaymentQuery);
                            JOptionPane.showMessageDialog((Component)null, "Your payment was Successful. Enjoy your Journey");
                        } else {
                            JOptionPane.showMessageDialog((Component)null, "No passenger found with the given PNR.");
                        }
                    }
                } catch (SQLException var12) {
                    JOptionPane.showMessageDialog((Component)null, "Error processing payment: " + var12.getMessage());
                }

            }
        });
        this.setSize(860, 490);
        this.setLocation(0, 0);
        this.setVisible(true);
    }
}
