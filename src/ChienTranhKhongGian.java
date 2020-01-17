import javax.swing.*;
import java.awt.*;

public class ChienTranhKhongGian extends JFrame {

    public ChienTranhKhongGian() {

        initUI();
    }

    private void initUI() {

        add(new ManHinh());

        setTitle("Chiến tranh không gian - lớp LTU16B");
        setSize(ThongSoCaiDat.MANHINH_WIDTH, ThongSoCaiDat.MANHINH_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new ChienTranhKhongGian();
            ex.setVisible(true);
        });
    }
}