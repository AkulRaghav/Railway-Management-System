
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Help extends JFrame {
    public static void main(String[] args) {
        (new Help()).setVisible(true);
    }

    public Help() {
        super("Help");
        this.initialize();
    }

    private void initialize() {
        this.setForeground(Color.CYAN);
        this.setLayout((LayoutManager)null);
        JLabel NewLabel = new JLabel("");
        NewLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("icon/railman5.jpg")));
        NewLabel.setBounds(100, 0, 1035, 656);
        this.add(NewLabel);
        this.setSize(1950, 1090);
        this.setLocation(0, 0);
        this.setVisible(true);
    }
}
