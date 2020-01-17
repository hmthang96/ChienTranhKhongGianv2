import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ManHinh extends JPanel {

    private Dimension d;
    private List<TauDich> aTauDich;
    private TauMinh tauMinh;
    private BanNhau bannhau;

    private int direction = -1;
    private int ketthuc = 0;

    private boolean inGame = true;
    private String explImg = "src/Images/explosion.png";
    private String thongbao = "Thua!!! Chơi lại -Y  Thoát -N";

    private Timer timer;


    public ManHinh() {

        initManHinh();
        gameInit();
    }

    private void initManHinh() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(ThongSoCaiDat.MANHINH_WIDTH, ThongSoCaiDat.MANHINH_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(ThongSoCaiDat.TocDoGame, new GameCycle());
        timer.start();

        gameInit();
    }


    private void gameInit() {

        aTauDich = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < ThongSoCaiDat.SoLuongTauDich / 4; j++) {

                var alien = new TauDich(ThongSoCaiDat.ATAUDICH_INIT_X + 18 * j,
                        ThongSoCaiDat.ATAUDICH_INIT_Y + 18 * i);
                aTauDich.add(alien);
            }
        }

        tauMinh = new TauMinh();
        bannhau = new BanNhau();
    }

    private void drawTauDich(Graphics g) {

        for (TauDich aTauDich : aTauDich) {

            if (aTauDich.isVisible()) {

                g.drawImage(aTauDich.getImage(), aTauDich.getX(), aTauDich.getY(), this);
            }

            if (aTauDich.isHoatdong()) {

                aTauDich.die();
            }
        }
    }

    private void drawTauMinh(Graphics g) {

        if (tauMinh.isVisible()) {

            g.drawImage(tauMinh.getImage(), tauMinh.getX(), tauMinh.getY(), this);
        }

        if (tauMinh.isHoatdong()) {

            tauMinh.die();
            inGame = false;
        }
    }

    private void drawBanNhau(Graphics g) {

        if (bannhau.isVisible()) {

            g.drawImage(bannhau.getImage(), bannhau.getX(), bannhau.getY(), this);
        }
    }

    private void drawBombing(Graphics g) {

        for (TauDich a : aTauDich) {

            TauDich.Bomb b = a.getBomb();

            if (!b.isPhahuy()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            g.drawLine(0, ThongSoCaiDat.VachKeDich,
                    ThongSoCaiDat.MANHINH_WIDTH, ThongSoCaiDat.VachKeDich);

            drawTauDich(g);
            drawTauMinh(g);
            drawBanNhau(g);
            drawBombing(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, ThongSoCaiDat.MANHINH_WIDTH, ThongSoCaiDat.MANHINH_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, ThongSoCaiDat.MANHINH_WIDTH / 2 - 30, ThongSoCaiDat.MANHINH_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, ThongSoCaiDat.MANHINH_WIDTH / 2 - 30, ThongSoCaiDat.MANHINH_WIDTH - 100, 50);

        var small = new Font("Tahoma", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(thongbao, (ThongSoCaiDat.MANHINH_WIDTH - fontMetrics.stringWidth(thongbao)) / 2,
                ThongSoCaiDat.MANHINH_WIDTH / 2);
    }

    private void update() {

        if (ketthuc == ThongSoCaiDat.SoLuongTauDich) {

            inGame = false;
            timer.stop();
            thongbao = "Chúc bạn đã thắng!!! Chơi lại -Y  Thoát -N";
        }


        tauMinh.act();


        if (bannhau.isVisible()) {

            int bannhauX = bannhau.getX();
            int bannhauY = bannhau.getY();

            for (TauDich aTauDich : aTauDich) {

                int taudichX = aTauDich.getX();
                int taudichY = aTauDich.getY();

                if (aTauDich.isVisible() && bannhau.isVisible()) {
                    if (bannhauX >= (taudichX)
                            && bannhauX <= (taudichX + ThongSoCaiDat.ATAUDICH_WIDTH)
                            && bannhauY >= (taudichY)
                            && bannhauY <= (taudichY + ThongSoCaiDat.ATAUDICH_HEIGHT)) {

                        var ii = new ImageIcon(explImg);
                        aTauDich.setImage(ii.getImage());
                        aTauDich.setHoatdong(true);
                        ketthuc++;
                        bannhau.die();
                    }
                }
            }

            int y = bannhau.getY();
            y -= 4;

            if (y < 0) {
                bannhau.die();
            } else {
                bannhau.setY(y);
            }
        }

        // aliens

        for (TauDich aTauDich : aTauDich) {

            int x = aTauDich.getX();

            if (x >= ThongSoCaiDat.MANHINH_WIDTH - ThongSoCaiDat.MANHINH_RIGHT && direction != -1) {

                direction = -1;

                Iterator<TauDich> i1 = this.aTauDich.iterator();

                while (i1.hasNext()) {

                    TauDich a2 = i1.next();
                    a2.setY(a2.getY() + ThongSoCaiDat.DichChuyenXuong);
                }
            }

            if (x <= ThongSoCaiDat.MANHINH_LEFT && direction != 1) {

                direction = 1;

                Iterator<TauDich> i2 = this.aTauDich.iterator();

                while (i2.hasNext()) {

                    TauDich a = i2.next();
                    a.setY(a.getY() + ThongSoCaiDat.DichChuyenXuong);
                }
            }
        }

        Iterator<TauDich> it = aTauDich.iterator();

        while (it.hasNext()) {

            TauDich aTauDich = it.next();

            if (aTauDich.isVisible()) {

                int y = aTauDich.getY();

                if (y > ThongSoCaiDat.VachKeDich - ThongSoCaiDat.ATAUDICH_HEIGHT) {
                    inGame = false;
                    thongbao = "Dich Da Vuot Qua Lop Phong Thu";
                }

                aTauDich.act(direction);
            }
        }


        var generator = new Random();

        for (TauDich aTauDich : aTauDich) {

            int shot = generator.nextInt(25);
            TauDich.Bomb bomb = aTauDich.getBomb();

            if (shot == ThongSoCaiDat.CHANCE && aTauDich.isVisible() && bomb.isPhahuy()) {

                bomb.setPhaHuy(false);
                bomb.setX(aTauDich.getX());
                bomb.setY(aTauDich.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int TauMinhX = tauMinh.getX();
            int TauMinhY = tauMinh.getY();

            if (tauMinh.isVisible() && !bomb.isPhahuy()) {

                if (bombX >= (TauMinhX)
                        && bombX <= (TauMinhX + ThongSoCaiDat.TauMinh_WIDTH)
                        && bombY >= (TauMinhY)
                        && bombY <= (TauMinhY + ThongSoCaiDat.TauMinh_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    tauMinh.setImage(ii.getImage());
                    tauMinh.setHoatdong(true);
                    bomb.setPhaHuy(true);
                }
            }

            if (!bomb.isPhahuy()) {

                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= ThongSoCaiDat.VachKeDich - ThongSoCaiDat.BOMB_HEIGHT) {

                    bomb.setPhaHuy(true);
                }
            }
        }
    }

    private void doGameCycle() {

        update();
        repaint();
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            tauMinh.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            tauMinh.keyPressed(e);

            int x = tauMinh.getX();
            int y = tauMinh.getY();

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_Y) {
                ChienTranhKhongGian a = new ChienTranhKhongGian();
                setVisible(false);
                a.setVisible(true);
            }
            if (key == KeyEvent.VK_N) {
                System.exit(0);
            }
            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                    if (!bannhau.isVisible()) {
                        bannhau = new BanNhau(x, y);

                    }
                }
            }
        }
    }
}