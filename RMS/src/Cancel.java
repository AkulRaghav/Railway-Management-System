
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cancel extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    public static void main(String[] args) {
        new Cancel();
    }

    public Cancel() {
        this.initialize();
    }

    private void initialize() {
        this.setTitle("CANCELLATION");
        this.getContentPane().setBackground(Color.WHITE);
        this.setBounds(100, 100, 801, 472);
        this.setLayout((LayoutManager)null);
        JLabel Cancellation = new JLabel("CANCELLATION");
        Cancellation.setFont(new Font("Tahoma", 0, 31));
        Cancellation.setForeground(Color.BLUE);
        Cancellation.setBounds(185, 24, 259, 38);
        this.add(Cancellation);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel NewLabel = new JLabel(i3);
        NewLabel.setBounds(470, 100, 250, 250);
        this.add(NewLabel);
        JLabel PassengerNo = new JLabel("PASSENGER NO");
        PassengerNo.setFont(new Font("Tahoma", 0, 17));
        PassengerNo.setBounds(60, 100, 132, 26);
        this.add(PassengerNo);
        JLabel Name = new JLabel("Name");
        Name.setFont(new Font("Tahoma", 0, 17));
        Name.setBounds(60, 150, 150, 27);
        this.add(Name);
        JLabel CancellationDate = new JLabel("CANCELLATION DATE");
        CancellationDate.setFont(new Font("Tahoma", 0, 17));
        CancellationDate.setBounds(60, 200, 180, 27);
        this.add(CancellationDate);
        JLabel Reason = new JLabel("Reason");
        Reason.setFont(new Font("Tahoma", 0, 17));
        Reason.setBounds(60, 250, 150, 27);
        this.add(Reason);
        JLabel Flightcode = new JLabel("RAILWAY_CODE");
        Flightcode.setFont(new Font("Tahoma", 0, 17));
        Flightcode.setBounds(60, 300, 150, 27);
        this.add(Flightcode);
        JButton Cancel = new JButton("CANCEL TICKET");
        Cancel.setFont(new Font("Tahoma", 0, 14));
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);
        Cancel.setBounds(250, 350, 150, 30);
        this.add(Cancel);
        this.textField = new JTextField();
        this.textField.setBounds(250, 100, 150, 27);
        this.add(this.textField);
        JButton checkButton = new JButton("Check");
        checkButton.setFont(new Font("Tahoma", 0, 14));
        checkButton.setBackground(Color.BLUE);
        checkButton.setForeground(Color.WHITE);
        checkButton.setBounds(410, 100, 80, 27);
        this.add(checkButton);
        this.textField_1 = new JTextField();
        this.textField_1.setBounds(250, 150, 150, 27);
        this.add(this.textField_1);
        this.textField_2 = new JTextField();
        this.textField_2.setBounds(250, 200, 150, 27);
        this.add(this.textField_2);
        this.textField_3 = new JTextField();
        this.textField_3.setBounds(250, 250, 150, 27);
        this.add(this.textField_3);
        this.textField_4 = new JTextField();
        this.textField_4.setBounds(250, 300, 150, 27);
        this.add(this.textField_4);
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String pnr = Cancel.this.textField.getText();

                try {
                    conn c = new conn();
                    String verifyQuery = "SELECT * FROM passenger WHERE PNR = ?";
                    PreparedStatement ps = c.c.prepareStatement(verifyQuery);
                    ps.setString(1, pnr);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        Cancel.this.textField_1.setText(rs.getString("Name"));
                        Cancel.this.textField_2.setText(rs.getString("Date"));
                        Cancel.this.textField_4.setText(rs.getString("Railway_Code"));
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "PNR not found.");
                    }
                } catch (SQLException var7) {
                    JOptionPane.showMessageDialog((Component)null, var7.getMessage());
                    var7.printStackTrace();
                }

            }
        });
        Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String pnr = Cancel.this.textField.getText();
                String name = Cancel.this.textField_1.getText();
                String date = Cancel.this.textField_2.getText();
                String reason = Cancel.this.textField_3.getText();
                String code = Cancel.this.textField_4.getText();

                try {
                    conn c = new conn();
                    String createCancelTableQuery = "    CREATE TABLE IF NOT EXISTS cancel (\n        PNR INT PRIMARY KEY,\n        Name VARCHAR(50),\n        CancellationDate VARCHAR(20),\n        Reason VARCHAR(100),\n        RailwayCode VARCHAR(20)\n    )\n";
                    c.s.executeUpdate(createCancelTableQuery);
                    String cancelQuery = "INSERT INTO cancel (PNR, Name, CancellationDate, Reason, RailwayCode) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement ps = c.c.prepareStatement(cancelQuery);
                    ps.setString(1, pnr);
                    ps.setString(2, name);
                    ps.setString(3, date);
                    ps.setString(4, reason);
                    ps.setString(5, code);
                    ps.executeUpdate();
                    String deleteQuery = "DELETE FROM passenger WHERE PNR = ?";
                    ps = c.c.prepareStatement(deleteQuery);
                    ps.setString(1, pnr);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog((Component)null, "SUCCESS. Your ticket has been cancelled.");
                    Cancel.this.setVisible(false);
                } catch (SQLException var12) {
                    JOptionPane.showMessageDialog((Component)null, var12.getMessage());
                    var12.printStackTrace();
                }

            }
        });
        this.setSize(860, 500);
        this.setVisible(true);
        this.setLocation(0, 0);
    }
}
