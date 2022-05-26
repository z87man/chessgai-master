package cn.openpool.chess.client;

import cn.hutool.core.io.FileUtil;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class Login extends JFrame {

    private Container contentPane;
    public static String userName;
    public static String password;
    public static File file;

    public Login() {
        setTitle("用户登录");
        setResizable(false);
        contentPane = getContentPane();
        setSize(620, 454);
        Point point = new Point(500, 500);
        setLocation(point);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * 判断登录信息文件
         * 读入 创建
         * */
        file = new File("user.ch");
        try {
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
            }
//            String[] split = new String(IOUtils.readFully(new FileInputStream(file), -1, true)).split("-");

            StringBuffer stringBuffer = new StringBuffer();
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(1);
            while (channel.read(allocate) > 0) {
                allocate.flip();
                stringBuffer.append(new String(allocate.array()));
            }
            String[] split = stringBuffer.toString().split("-");
            if (split.length > 1) {
                userName = split[0];
                password = split[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        JLabel back = new JLabel(new ImageIcon("img/logBack.png"));
        back.setBounds(100, 100, 410, 250);

        JTextField jTextField = new JTextField(12);
        JPasswordField jPasswordField = new JPasswordField(13);
        JLabel jLabel1 = new JLabel("用户名");
        JLabel jLabel2 = new JLabel("密码");
        JButton jButton = new JButton("登录");
        JLabel error = new JLabel("密码或账号错误");
        error.setVisible(false);

        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (userName == null) {
                    userName = jTextField.getText();
                    password = new String(jPasswordField.getPassword());
                    FileUtil.writeString(userName + "-" + password, file, "UTF-8");
                }
                if (password.equals(new String(jPasswordField.getPassword()))) {
                    userName = jTextField.getText();
                    setVisible(false);
                    Game.draw();
                } else {
                    error.setVisible(true);
                }
            }
        });

        JLabel text = new JLabel(
                "                                 " +
                        "                                 国际象棋" +
                        "                                      " +
                        "                                      "
        );

        JPanel title = new JPanel();
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();

        title.add(text);

        jp1.add(jLabel1);
        jp1.add(jTextField);

        jp2.add(jLabel2);
        jp2.add(jPasswordField);

        jp3.add(jButton);
        jp3.add(error);

        contentPane.setLayout(new FlowLayout());
        this.getLayeredPane().add(back, new Integer(Integer.MIN_VALUE));
        ((JPanel) contentPane).setOpaque(false);

        contentPane.add(title);
        contentPane.add(jp1);
        contentPane.add(jp2);
        contentPane.add(jp3);

        setVisible(true);
    }
}
