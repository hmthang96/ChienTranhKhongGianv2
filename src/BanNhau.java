import javax.swing.*;

public class BanNhau extends GiaoDien {

    public BanNhau() {
    }

    public BanNhau(int x, int y) {

        initBanNhau(x, y);
    }

    private void initBanNhau(int x, int y) {

        var bannhauImg = "src/Images/shot.png";
        var ii = new ImageIcon(bannhauImg);
        setImage(ii.getImage());

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}