
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class Journey_Details extends JFrame {
    private JTable table;
    private JComboBox<String> sourceComboBox;
    private JComboBox<String> destinationComboBox;

    public static void main(String[] args) {
        (new Journey_Details()).setVisible(true);
    }

    public Journey_Details() {
        this.getContentPane().setBackground(Color.WHITE);
        this.getContentPane().setFont(new Font("Tahoma", 0, 13));
        this.setDefaultCloseOperation(3);
        this.setSize(860, 523);
        this.setLayout((LayoutManager)null);
        this.setVisible(true);
        JLabel title = new JLabel("---: JOURNEY DETAILS :---");
        title.setFont(new Font("Tahoma", 0, 31));
        title.setForeground(Color.BLUE);
        title.setBounds(50, 20, 570, 35);
        this.add(title);
        JLabel sourceLabel = new JLabel("SOURCE");
        sourceLabel.setFont(new Font("Tahoma", 0, 17));
        sourceLabel.setBounds(50, 100, 200, 30);
        this.add(sourceLabel);
        this.sourceComboBox = new JComboBox(new String[]{"BANGALORE", "CHENNAI", "DELHI", "HYDERABAD", "MUMBAI"});
        this.sourceComboBox.setBounds(220, 100, 200, 30);
        this.add(this.sourceComboBox);
        JLabel destinationLabel = new JLabel("DESTINATION");
        destinationLabel.setFont(new Font("Tahoma", 0, 17));
        destinationLabel.setBounds(50, 150, 200, 30);
        this.add(destinationLabel);
        this.destinationComboBox = new JComboBox(new String[]{"BANGALORE", "CHENNAI", "DELHI", "HYDERABAD", "MUMBAI"});
        this.destinationComboBox.setBounds(220, 150, 200, 30);
        this.add(this.destinationComboBox);
        JButton btnShow = new JButton("SHOW");
        btnShow.setFont(new Font("Tahoma", 0, 20));
        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String source = (String)Journey_Details.this.sourceComboBox.getSelectedItem();
                String destination = (String)Journey_Details.this.destinationComboBox.getSelectedItem();

                try {
                    conn c = new conn();
                    String str = "select * from jrny_details where Source = '" + source + "' and Destination = '" + destination + "'";
                    ResultSet rs = c.s.executeQuery(str);
                    Journey_Details.this.table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException var7) {
                    var7.printStackTrace();
                }

            }
        });
        btnShow.setBounds(220, 200, 120, 30);
        this.add(btnShow);
        JButton btnMM = new JButton("Main Menu");
        btnMM.setFont(new Font("Tahoma", 0, 20));
        btnMM.setBounds(400, 200, 120, 30);
        this.add(btnMM);
        btnMM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    Journey_Details.this.setVisible(false);
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
        this.table = new JTable();
        this.table.setBackground(Color.WHITE);
        this.table.setBounds(23, 250, 800, 300);
        JScrollPane pane = new JScrollPane(this.table);
        pane.setBounds(23, 250, 800, 300);
        pane.setBackground(Color.WHITE);
        this.add(pane);
        JLabel note = new JLabel("Note: You can check your preferred train details in 'Railway System -> Railway_Info' with the help of Train Number.");
        note.setFont(new Font("Tahoma", 0, 13));
        note.setForeground(Color.RED);
        note.setBounds(23, 220, 800, 30);
        this.add(note);
    }
}
