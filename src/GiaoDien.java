import java.awt.*;

public class GiaoDien {

    private boolean visible;
    private Image image;
    private boolean hoatdong;

    int x;
    int y;
    int dx;

    public GiaoDien() {

        visible = true;
    }

    public void die() {

        visible = false;
    }

    public boolean isVisible() {

        return visible;
    }

    protected void setVisible(boolean visible) {

        this.visible = visible;
    }

    public void setImage(Image image) {

        this.image = image;
    }

    public Image getImage() {

        return image;
    }

    public void setX(int x) {

        this.x = x;
    }

    public void setY(int y) {

        this.y = y;
    }

    public int getY() {

        return y;
    }

    public int getX() {

        return x;
    }

    public void setHoatdong(boolean hoatdong) {

        this.hoatdong = hoatdong;
    }

    public boolean isHoatdong() {

        return this.hoatdong;
    }
}