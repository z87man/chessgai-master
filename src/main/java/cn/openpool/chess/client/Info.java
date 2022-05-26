package cn.openpool.chess.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;


public class Info extends JFrame {
    private Container contentPane;

    public Info() {
        setTitle("对局消息");
        contentPane = getContentPane();
        setSize(300, 200);
        Point point = new Point(500, 500);
        setLocation(point);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void draw(String msg) {
        JLabel jLabel1 = new JLabel(msg);
        JButton yes = new JButton("重来");
        JButton no = new JButton("退出游戏");
        yes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChessWindows.chess = new ChessType[][]{
                        {
                                ChessType.Black_Rook,
                                ChessType.Black_Knight,
                                ChessType.Black_Bishop,
                                ChessType.Black_Queen,
                                ChessType.Black_King,
                                ChessType.Black_Bishop,
                                ChessType.Black_Knight,
                                ChessType.Black_Rook
                        },
                        {
                                ChessType.Black_Pawn,
                                ChessType.Black_Pawn,
                                ChessType.Black_Pawn,
                                ChessType.Black_Pawn,
                                ChessType.Black_Pawn,
                                ChessType.Black_Pawn,
                                ChessType.Black_Pawn,
                                ChessType.Black_Pawn,
                        },
                        {
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                        },
                        {
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                        },
                        {
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                        },
                        {
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                                ChessType.Empty,
                        },
                        {
                                ChessType.White_Pawn,
                                ChessType.White_Pawn,
                                ChessType.White_Pawn,
                                ChessType.White_Pawn,
                                ChessType.White_Pawn,
                                ChessType.White_Pawn,
                                ChessType.White_Pawn,
                                ChessType.White_Pawn,
                        },
                        {
                                ChessType.White_Rook,
                                ChessType.White_Knight,
                                ChessType.White_Bishop,
                                ChessType.White_Queen,
                                ChessType.White_King,
                                ChessType.White_Bishop,
                                ChessType.White_Knight,
                                ChessType.White_Rook
                        }

                };
                Game.draw();
                ChessWindows.record = new Stack<>();
                dispose();
            }
        });
        no.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(1);
            }
        });
        setLayout(new GridLayout(2, 1));
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        jp1.add(jLabel1);
        jp2.add(yes);
        jp2.add(no);

        contentPane.add(jp1);
        contentPane.add(jp2);
        setVisible(true);
    }
}
