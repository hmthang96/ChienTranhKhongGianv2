import com.sun.tools.javac.Main;

import javax.swing.*;

public class TauDich extends GiaoDien {

    private Bomb bomb;

    public TauDich(int x, int y) {

        initTauDich(x, y);
    }

    private void initTauDich(int x, int y) {

        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
//         var TauDichImg = Main.class.getResource("src/Images/alien.png");
        var TauDichImg = "//Images//alien.png";
        var ii = new ImageIcon(TauDichImg);

        setImage(ii.getImage());
    }

    public void act(int direction) {
        this.x += direction;
    }

    public Bomb getBomb() {

        return bomb;
    }

    public class Bomb extends GiaoDien {

        private boolean phahuy;

        public Bomb(int x, int y) {

            initBomb(x, y);
        }

        private void initBomb(int x, int y) {

            setPhaHuy(true);

            this.x = x;
            this.y = y;
            var bombImg = "src/Images/bomb.png";
            var ii = new ImageIcon(bombImg);
            setImage(ii.getImage());
        }

        public void setPhaHuy(boolean phahuy) {

            this.phahuy = phahuy;
        }

        public boolean isPhahuy() {

            return phahuy;
        }
    }
}