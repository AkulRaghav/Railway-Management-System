
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class Railway_Info extends JFrame {
    private JTable table;
    private JTextField textField;

    public static void main(String[] args) {
        (new Railway_Info()).setVisible(true);
    }

    public Railway_Info() {
        this.getContentPane().setBackground(Color.WHITE);
        this.getContentPane().setFont(new Font("Tahoma", 0, 13));
        this.setDefaultCloseOperation(3);
        this.setSize(860, 523);
        this.setLayout((LayoutManager)null);
        this.setVisible(true);
        JLabel Fcode = new JLabel("TRAIN NO");
        Fcode.setFont(new Font("Tahoma", 0, 17));
        Fcode.setBounds(50, 100, 200, 30);
        this.add(Fcode);
        JLabel RailwayDetails = new JLabel("---: RAILWAY INFORMATION :---");
        RailwayDetails.setFont(new Font("Tahoma", 0, 31));
        RailwayDetails.setForeground(Color.BLUE);
        RailwayDetails.setBounds(50, 20, 570, 35);
        this.add(RailwayDetails);
        JButton btnShow = new JButton("SHOW");
        btnShow.setFont(new Font("Tahoma", 0, 20));
        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String code = Railway_Info.this.textField.getText();

                try {
                    conn c = new conn();
                    String str = "select Railway_Code,Date,Source,Destination,Capacity,Class_Code,Class_Name,Fare from sector where Railway_Code = '" + code + "'";
                    ResultSet rs = c.s.executeQuery(str);
                    Railway_Info.this.table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException var6) {
                    var6.printStackTrace();
                }

            }
        });
        btnShow.setBounds(220, 150, 120, 30);
        this.add(btnShow);
        this.table = new JTable();
        this.table.setBackground(Color.WHITE);
        this.table.setBounds(23, 250, 800, 300);
        JScrollPane pane = new JScrollPane(this.table);
        pane.setBounds(23, 250, 800, 300);
        pane.setBackground(Color.WHITE);
        this.add(pane);
        this.textField = new JTextField();
        this.textField.setBounds(220, 100, 200, 30);
        this.add(this.textField);
        this.setDefaultCloseOperation(1);
        this.setSize(900, 650);
        this.setVisible(true);
        this.setLocation(0, 0);
    }
}
