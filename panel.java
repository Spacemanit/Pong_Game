import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class panel extends JPanel implements ActionListener {
    Timer timer;
    JLabel pbox = new JLabel();
    JLabel cbox = new JLabel();
    final int px = 0, cx = 774, bh = 10, bw = 10;
    int py = 188, cy = 188, bx = 380, by = 275, b_yvel = 2, b_xvel = 2, ps = 0, cs = 0;
    Rectangle rect_ball;
    Rectangle rect_pbox;
    Rectangle rect_cbox;
    boolean pcol = false, ccol = false, go = false;
    Action cup;
    Action cdo;
    Action pup;
    Action pdo;

    panel() {
        setSize(800, 600);
        setBackground(Color.black);
        setVisible(true);
        setFocusable(true);
        setLayout(null);

        pbox.setBounds(px, 188, 10, 100);
        cbox.setBounds(cx, 188, 10, 100);

        cup = new cup();
        pup = new pup();
        cdo = new cdo();
        pdo = new pdo();

        pbox.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "pup");
        pbox.getActionMap().put("pup", pup);
        pbox.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "pdo");
        pbox.getActionMap().put("pdo", pdo);
        cbox.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "cup");
        cbox.getActionMap().put("cup", cup);
        cbox.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "cdo");
        cbox.getActionMap().put("cdo", cdo);

        add(pbox);
        add(cbox);

        timer = new Timer(1, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.gray);
        float[] dashingPattern1 = new float[]{25.0F, 5.0F};
        Stroke stroke1 = new BasicStroke(1.0F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0F, dashingPattern1, 2.0F);
        g2d.setStroke(stroke1);
        g2d.drawLine(385, 0, 385, 700);
        g2d.setColor(Color.green);

        g2d.fillRect(px, py, 10, 100);
        g2d.setColor(Color.red);
        g2d.fillRect(cx, cy, 10, 100);
        g2d.setColor(Color.gray);
        g2d.fillRoundRect(bx, by, bw, bh, 50, 50);
        g2d.setColor(Color.gray);

        g2d.setFont(new Font("Courier New", Font.PLAIN, 30));
        g2d.drawString(Integer.toString(ps), 350, 20);
        g2d.drawString(Integer.toString(cs), 400, 20);


        if (ps == 5) {
            g2d.setColor(Color.black);
            g2d.drawRect(0, 0, 800, 600);
            g2d.setColor(Color.green);
            g2d.drawString("GREEN WINS", 300, 175);
        } else if (cs == 5) {
            g2d.setColor(Color.black);
            g2d.drawRect(0, 0, 800, 600);
            g2d.setColor(Color.red);
            g2d.drawString(" RED WINS ", 300, 175);
        }

        if (ps == 5 || cs == 5) {
            go = true;
            g2d.drawString("PRESS ANYWHERE TO CONTINUE", 150, 375);
            timer.stop();
        }
        repaint();
        collision();
    }

    public void collision() {
        if (((bx <= px + 10) && (bx >= px)) && (by >= py) && (by <= py + 100)) {
            pcol = true;
        }
        if (((bx + bw >= cx) && (bx + bw <= cx + 10)) && (by >= cy) && (by <= cy + 100)) {
            ccol = true;
        }
    }

    public void actionPerformed(ActionEvent e) {

        rect_ball = new Rectangle(bx, by, bw, bh);
        rect_pbox = new Rectangle(px, py, 10, 100);
        rect_cbox = new Rectangle(cx, cy, 10, 100);
        if (bx + bw <= 0) {
            b_xvel = 2;
            ++cs;
            bx = 370;
            by = cy + 100;
        }

        if (bx >= 800) {
            b_xvel = -2;
            ++ps;
            bx = 370;
            by = py + 100;
        }

        if (pcol) {
            b_xvel = Math.abs(b_xvel);
            ++b_xvel;
            pcol = false;
        }

        if (ccol) {
            b_xvel *= -1;
            --b_xvel;
            ccol = false;
        }

        bx += b_xvel;
        by += b_yvel;
        if (by + bh >= 560) {
            b_yvel = -2;
        }

        if (by <= 0) {
            b_yvel = 2;
        }
    }
    public class cup extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
             if (cy >= 0) cy -= 50;
        }
    }
    public class cdo extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cy + 150 <= 600) cy += 50;
        }
    }
    public class pup extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (py >= 0) py -= 50;
        }
    }
    public class pdo extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (py + 150 <= 600) py += 50;
        }
    }
}
