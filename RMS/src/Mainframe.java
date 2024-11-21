
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Mainframe extends JFrame {
    public static void main(String[] args) {
        (new Mainframe()).setVisible(true);
    }

    public Mainframe() {
        super("RAILWAY RESERVATION MANAGEMENT SYSTEM");
        this.initialize();
    }

    private void initialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setDefaultCloseOperation(3);
        this.setLayout((LayoutManager)null);
        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("icon/railman2.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(screenSize.width, screenSize.height, 4);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, screenSize.width, screenSize.height);
        this.add(background);
        JLabel titleLabel = new JLabel("INDIAN RAILWAY WELCOMES YOU", 0);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Calibri", 1, 36));
        titleLabel.setBounds(screenSize.width / 4, screenSize.height / 10, screenSize.width / 2, 50);
        background.add(titleLabel);
        JLabel dashLabel = new JLabel("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾", 0);
        dashLabel.setForeground(Color.WHITE);
        dashLabel.setFont(new Font("Calibri", 1, 20));
        dashLabel.setBounds(screenSize.width / 4, screenSize.height / 7, screenSize.width / 2, 20);
        background.add(dashLabel);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(32, 42, 68));
        this.setJMenuBar(menuBar);
        JMenu railwayMenu = new JMenu("RAILWAY SYSTEM");
        railwayMenu.setForeground(Color.WHITE);
        menuBar.add(railwayMenu);
        JMenuItem journeyDetails = new JMenuItem("JOURNEY_DETAILS");
        railwayMenu.add(journeyDetails);
        JMenuItem railwayInfo = new JMenuItem("RAILWAY_INFO");
        railwayMenu.add(railwayInfo);
        JButton bookTicket = this.createMenuButton("BOOK TICKET");
        JButton payment = this.createMenuButton("PAYMENT");
        JButton cancellation = this.createMenuButton("CANCELLATION");
        JButton pnrEnquiry = this.createMenuButton("PNR ENQUIRY");
        JButton help = this.createMenuButton("HELP");
        menuBar.add(bookTicket);
        menuBar.add(payment);
        menuBar.add(cancellation);
        menuBar.add(pnrEnquiry);
        menuBar.add(help);
        journeyDetails.addActionListener((e) -> {
            this.openJourneyDetails();
        });
        railwayInfo.addActionListener((e) -> {
            this.openRailwayInfo();
        });
        bookTicket.addActionListener((e) -> {
            this.openBookTicket();
        });
        payment.addActionListener((e) -> {
            this.openPayment();
        });
        cancellation.addActionListener((e) -> {
            this.openCancellation();
        });
        pnrEnquiry.addActionListener((e) -> {
            this.openPNREnquiry();
        });
        help.addActionListener((e) -> {
            this.openHelp();
        });
        this.setLocation(0, 0);
        this.setVisible(true);
    }

    private JButton createMenuButton(String label) {
        JButton button = new JButton(label);
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusable(false);
        button.setForeground(Color.WHITE);
        return button;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog((Component)null, message);
    }

    private void openJourneyDetails() {
        try {
            new Journey_Details();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private void openRailwayInfo() {
        try {
            new Railway_Info();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private void openBookTicket() {
        try {
            new Add_Customer();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private void openPayment() {
        try {
            new Payment_Details();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private void openCancellation() {
        try {
            new Cancel();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private void openPNREnquiry() {
        try {
            PnrEnquery pnrEnquery = new PnrEnquery();
            pnrEnquery.setVisible(true);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private void openHelp() {
        try {
            new Help();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
