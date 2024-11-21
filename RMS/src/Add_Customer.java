
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Add_Customer extends JFrame {
    HashMap<String, Integer> fareMap;
    JTextField textField_2;
    JTextField textField_3;
    JTextField textField_4;
    JTextField textField_5;
    JTextField textField_6;
    JLabel Date;

    public Add_Customer() {
        this.getContentPane().setForeground(Color.BLUE);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("BOOK YOUR TICKET");
        this.setDefaultCloseOperation(1);
        this.setSize(778, 486);
        this.getContentPane().setLayout((LayoutManager)null);
        this.fareMap = new HashMap();
        this.fareMap.put("BANGALORE-CHENNAI", 500);
        this.fareMap.put("BANGALORE-DELHI", 1500);
        this.fareMap.put("BANGALORE-HYDERABAD", 700);
        this.fareMap.put("BANGALORE-MUMBAI", 1200);
        this.fareMap.put("BANGALORE-PATNA", -1);
        this.fareMap.put("CHENNAI-DELHI", 1400);
        this.fareMap.put("CHENNAI-HYDERABAD", 600);
        this.fareMap.put("CHENNAI-MUMBAI", 1100);
        this.fareMap.put("CHENNAI-PATNA", -1);
        this.fareMap.put("DELHI-HYDERABAD", 1700);
        this.fareMap.put("DELHI-MUMBAI", 1300);
        this.fareMap.put("DELHI-PATNA", -1);
        this.fareMap.put("HYDERABAD-MUMBAI", 800);
        this.fareMap.put("HYDERABAD-PATNA", -1);
        this.fareMap.put("MUMBAI-PATNA", -1);
        JLabel Railwaycode = new JLabel("RAILWAY CODE");
        Railwaycode.setFont(new Font("Tahoma", 0, 18));
        Railwaycode.setBounds(60, 30, 150, 25);
        this.add(Railwaycode);
        this.textField_6 = new JTextField();
        this.textField_6.setBounds(200, 30, 150, 25);
        this.add(this.textField_6);
        JLabel Source = new JLabel("SOURCE");
        Source.setFont(new Font("Tahoma", 0, 18));
        Source.setBounds(60, 65, 150, 25);
        this.add(Source);
        JLabel Destination = new JLabel("DESTINATION");
        Destination.setFont(new Font("Tahoma", 0, 18));
        Destination.setBounds(60, 100, 150, 25);
        this.add(Destination);
        String[] locations = new String[]{"BANGALORE", "MUMBAI", "CHENNAI", "PATNA", "DELHI", "HYDERABAD"};
        final JComboBox<String> comboBox = new JComboBox(locations);
        comboBox.setBounds(200, 65, 150, 22);
        this.add(comboBox);
        final JComboBox<String> comboBox_1 = new JComboBox(locations);
        comboBox_1.setBounds(200, 100, 150, 22);
        this.add(comboBox_1);
        JLabel Date = new JLabel("DATE");
        Date.setFont(new Font("Tahoma", 0, 18));
        Date.setBounds(60, 135, 200, 25);
        this.add(Date);
        final JTextField textField_date = new JTextField();
        textField_date.setBounds(200, 135, 150, 20);
        textField_date.setEditable(false);
        textField_date.setText(LocalDate.now().toString());
        this.add(textField_date);
        JLabel Name = new JLabel("NAME");
        Name.setFont(new Font("Tahoma", 0, 18));
        Name.setBounds(60, 170, 150, 25);
        this.add(Name);
        this.textField_4 = new JTextField();
        this.textField_4.setBounds(200, 170, 150, 20);
        this.add(this.textField_4);
        JLabel Gender = new JLabel("GENDER");
        Gender.setFont(new Font("Tahoma", 0, 18));
        Gender.setBounds(60, 205, 150, 25);
        this.add(Gender);
        final JRadioButton Male = new JRadioButton("MALE");
        Male.setBackground(Color.WHITE);
        Male.setBounds(200, 205, 70, 20);
        this.add(Male);
        final JRadioButton Female = new JRadioButton("FEMALE");
        Female.setBackground(Color.WHITE);
        Female.setBounds(280, 205, 70, 20);
        this.add(Female);
        final JRadioButton Others = new JRadioButton("Others");
        Others.setBackground(Color.WHITE);
        Others.setBounds(360, 205, 70, 20);
        this.add(Others);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(Male);
        genderGroup.add(Female);
        genderGroup.add(Others);
        JLabel Adhar = new JLabel("AADHAR NO.");
        Adhar.setFont(new Font("Tahoma", 0, 18));
        Adhar.setBounds(60, 240, 150, 25);
        this.add(Adhar);
        this.textField_3 = new JTextField();
        this.textField_3.setBounds(200, 240, 150, 25);
        this.add(this.textField_3);
        JLabel Address = new JLabel("ADDRESS");
        Address.setFont(new Font("Tahoma", 0, 18));
        Address.setBounds(60, 275, 150, 25);
        this.add(Address);
        this.textField_2 = new JTextField();
        this.textField_2.setBounds(200, 275, 150, 25);
        this.add(this.textField_2);
        JLabel Phno = new JLabel("PH NO");
        Phno.setFont(new Font("Tahoma", 0, 18));
        Phno.setBounds(60, 310, 150, 25);
        this.add(Phno);
        this.textField_5 = new JTextField();
        this.textField_5.setBounds(200, 310, 150, 25);
        this.add(this.textField_5);
        JLabel note = new JLabel("After saving your information, GO to Payment Section to pay your Fare.");
        note.setFont(new Font("Tahoma", 0, 13));
        note.setForeground(Color.RED);
        note.setBounds(60, 350, 500, 25);
        this.add(note);
        JButton Save = new JButton("SAVE");
        Save.setBounds(60, 400, 290, 30);
        Save.setBackground(Color.BLACK);
        Save.setForeground(Color.WHITE);
        this.add(Save);
        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String railwayCode = Add_Customer.this.textField_6.getText().trim();
                String source = (String)comboBox.getSelectedItem();
                String destination = (String)comboBox_1.getSelectedItem();
                String date = textField_date.getText();
                String name = Add_Customer.this.textField_4.getText().trim();
                String address = Add_Customer.this.textField_2.getText().trim();
                String aadhar = Add_Customer.this.textField_3.getText().trim();
                String phone = Add_Customer.this.textField_5.getText().trim();
                String gender = Male.isSelected() ? "Male" : (Female.isSelected() ? "Female" : (Others.isSelected() ? "Others" : null));
                if (railwayCode.isEmpty()) {
                    JOptionPane.showMessageDialog((Component)null, "Railway Code cannot be empty.");
                } else if (source.equals(destination)) {
                    JOptionPane.showMessageDialog((Component)null, "Source and Destination cannot be the same.");
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog((Component)null, "Name cannot be empty.");
                } else if (gender == null) {
                    JOptionPane.showMessageDialog((Component)null, "Please select a gender.");
                } else if (!aadhar.isEmpty() && aadhar.matches("\\d{12}")) {
                    if (address.isEmpty()) {
                        JOptionPane.showMessageDialog((Component)null, "Address cannot be empty.");
                    } else if (!phone.isEmpty() && phone.matches("\\d{10}")) {
                        String routeKey = source + "-" + destination;
                        Integer fare = (Integer)Add_Customer.this.fareMap.get(routeKey);
                        if (fare != null && fare != -1) {
                            try {
                                conn c = new conn();
                                String createTableQuery = "    CREATE TABLE IF NOT EXISTS passenger (\n        pnr INT PRIMARY KEY, -- PNR is the primary key\n        railway_code VARCHAR(20),\n        source VARCHAR(50),\n        destination VARCHAR(50),\n        date VARCHAR(20),\n        name VARCHAR(50),\n        gender VARCHAR(10),\n        aadhar VARCHAR(20),\n        address VARCHAR(100),\n        phone VARCHAR(15),\n        amount INT\n    )\n";
                                c.s.executeUpdate(createTableQuery);
                                SecureRandom random = new SecureRandom();

                                int pnr;
                                String insertQuery;
                                ResultSet rs;
                                do {
                                    pnr = 100000 + random.nextInt(900000);
                                    insertQuery = "SELECT COUNT(*) FROM passenger WHERE pnr = " + pnr;
                                    rs = c.s.executeQuery(insertQuery);
                                    rs.next();
                                } while(rs.getInt(1) != 0);

                                insertQuery = String.format("INSERT INTO passenger VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)", pnr, railwayCode, source, destination, date, name, gender, aadhar, address, phone, fare);
                                c.s.executeUpdate(insertQuery);
                                JOptionPane.showMessageDialog((Component)null, "Your PNR Number is " + pnr + ". Pay fare of ₹" + fare + " for successful booking.");
                                Add_Customer.this.setVisible(false);
                            } catch (Exception var19) {
                                JOptionPane.showMessageDialog((Component)null, var19.getMessage());
                                var19.printStackTrace();
                            }

                        } else {
                            JOptionPane.showMessageDialog((Component)null, "No fare available for the selected route.");
                        }
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "Enter a valid 10-digit phone number.");
                    }
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Enter a valid 12-digit Aadhar number.");
                }
            }
        });
        this.setVisible(true);
        this.setLocation(200, 100);
    }

    public static void main(String[] args) {
        new Add_Customer();
    }
}
