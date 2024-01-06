import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class frame implements ActionListener {
    panel p = new panel();
    JFrame f = new JFrame();
    JButton rb = new JButton();

    frame() {
        f.setTitle("Pong Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
        f.setBounds(500, 200, 800, 600);
        rb.setBounds(0, 0, 1000, 1000);
        rb.addActionListener(this);
        f.add(rb);
        f.setFocusable(true);
        f.add(p);
    }

    public static void main(String[] args) {
        new frame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.rb && (p.ps == 5 || p.cs == 5)) {
            new frame();
            f.dispose();
        }

    }
}