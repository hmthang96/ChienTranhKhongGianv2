import javax.swing.*;
import java.awt.event.KeyEvent;

public class TauMinh extends GiaoDien {

    private int width;

    public TauMinh() {

        initTauMinh();
    }

    private void initTauMinh() {

        var tauMinhImg = "src/Images/player.png";
        var ii = new ImageIcon(tauMinhImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());

        int START_X = 670;
        setX(START_X);

        int START_Y = 680;
        setY(START_Y);
    }

    public void act() {

        x += dx;

        if (x <= 2) {

            x = 2;
        }

        if (x >= ThongSoCaiDat.MANHINH_WIDTH - 2 * width) {

            x = ThongSoCaiDat.MANHINH_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }
}
