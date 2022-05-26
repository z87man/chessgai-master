package cn.openpool.chess.client;


import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;

public class Game {

    private static Container window;
    public static JFrame jf;
    private static int grids;
    private static int gridsSize;
    private static ItemRule itemRule;
    public static Integer count;
    public static Integer bItem;
    public static Integer wItem;
    public static JLabel nowType;
    public static JLabel countJl;
    public static JLabel b;
    public static JLabel w;
    private static JButton repent;
    private static Theme theme;
    public static Long timeOut;
    public static JLabel timeText;
    private static ClientPoint clientPoint = new ClientPoint();

    public static void draw() {
        theme = new Theme(Color.GRAY, Color.WHITE);
        grids = 8;
        gridsSize = 100;
        itemRule = new ItemRule();
        count = 1;
        bItem = 0;
        wItem = 0;
        timeOut = System.currentTimeMillis() + (1000 * 30);
        jf = new JFrame("国际象棋棋盘");
        Point point = new Point(500, 100);
        jf.setLocation(point);
        jf.setSize(1000, 840);
        jf.setLayout(new BorderLayout());
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window = jf.getContentPane();

        JMenuBar mb = new JMenuBar();
        // 菜单
        JFileChooser jFileChooser = new JFileChooser(System.getProperty("user.dir"));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JMenu save = new JMenu("保存游戏");
        /**
         * 保存游戏 至文件
         * */
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    jFileChooser.showOpenDialog(null);
                    File file = jFileChooser.getSelectedFile();
                    ObjectOutputStream obo = new ObjectOutputStream(new FileOutputStream(file));
                    SaveItem saveItem = new SaveItem(Game.bItem, Game.wItem, Game.count, ChessWindows.chess, ChessWindows.record);
                    obo.writeObject(saveItem);
                    obo.flush();
                    obo.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        JMenu port = new JMenu("导入游戏");
        port.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    jFileChooser.showOpenDialog(null);
                    File selectedFile = jFileChooser.getSelectedFile();
                    if (!selectedFile.getName().endsWith(".ch")) {
                        JOptionPane.showMessageDialog(null, "ERROR CODE: 104", "导入文件类型不正确", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ObjectInputStream obi = new ObjectInputStream(new FileInputStream(selectedFile));
                    SaveItem saveItem = null;
                    try {
                        saveItem = (SaveItem) obi.readObject();
                    } catch (IOException | ClassNotFoundException ioException) {
                        JOptionPane.showMessageDialog(null, "ERROR CODE: 102", "导入文件数据不正确，检查棋子数据", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (saveItem.getChess().length != 8) {
                        JOptionPane.showMessageDialog(null, "ERROR CODE:101", "导入文件的棋盘非8x8", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        for (ChessType[] chess : saveItem.getChess()) {
                            if (chess.length != 8) {
                                JOptionPane.showMessageDialog(null, "ERROR CODE:101", "导入文件的棋盘非8x8", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }
                    if (saveItem.getCount() == null) {
                        JOptionPane.showMessageDialog(null, "ERROR CODE:103", "导入数据无行棋方", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ChessWindows.chess = saveItem.getChess();
                    ChessWindows.record = saveItem.getRecord();
                    jf.dispose();
                    Game.draw();

                    Game.count = saveItem.getCount();
                    Game.bItem = saveItem.getbItem();
                    Game.wItem = saveItem.getwItem();

                    Game.b.setText("黑棋得分：" + bItem);
                    w.setText("白棋得分：" + wItem);
                    String nowText;
                    if ((count % 2) + 1 != 1) {
                        nowText = "当前棋子: 白色";
                    } else {
                        nowText = "当前棋子: 黑色";
                    }
                    Game.nowType.setText(nowText);
                    if (count % 2 != 0) {
                        countJl.setText("当前回合: " + ((count / 2) + 1));
                    } else {
                        countJl.setText("当前回合: " + ((count / 2)));
                    }

                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        mb.add(save);
        mb.add(port);
        jf.setJMenuBar(mb);

        // 顶部
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 7));

        JLabel name = new JLabel("用户：" + Login.userName, SwingConstants.CENTER);
        name.setSize(100, 40);
        JButton target = new JButton("切换主题");
        target.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (theme.getCurrent() == 1) {
                    theme = new Theme(Color.YELLOW, Color.PINK);
                    theme.setCurrent(2);
                } else if (theme.getCurrent() == 2) {
                    theme = new Theme(Color.magenta, Color.lightGray);
                    theme.setCurrent(3);
                } else {
                    theme = new Theme(Color.GRAY, Color.WHITE);
                    theme.setCurrent(1);
                }

                for (int y = 0; y < grids; y++) {
                    for (int x = 0; x < grids; x++) {
                        JLabel item = ChessWindows.chessItem.get(x + "-" + y).getjLabel();
                        if ((x + y) % 2 == 0) {
                            item.setBackground(theme.getB());
                        } else {
                            item.setBackground(theme.getW());
                        }
                    }
                }
            }
        });
        repent = new JButton("悔棋");
        repent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!ChessWindows.record.empty()) {
                    ChessCache pop = ChessWindows.record.pop();
                    pop.refresh();
                    timeOut = System.currentTimeMillis() + (1000 * 30);
                }
            }
        });
        JButton play = new JButton("重置棋盘");
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jf.dispose();
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
            }
        });
        String nowText;
        if ((count % 2) == 1) {
            nowText = "当前棋子: 白色";
        } else {
            nowText = "当前棋子: 黑色";
        }
        nowType = new JLabel(nowText, SwingConstants.CENTER);
        countJl = new JLabel("当前回合: " + ((count / 2)), SwingConstants.CENTER);

        timeText = new JLabel(("剩余" + (timeOut - System.currentTimeMillis()) / 1000) + "秒", SwingConstants.CENTER);
        top.add(name);
        top.add(target);
        top.add(play);
        top.add(repent);
        top.add(timeText);
        top.add(nowType);
        top.add(countJl);

        JPanel main = new JPanel();
        main.setLayout(new GridLayout(8, 8));
        for (int y = 0; y < grids; y++) {
            for (int x = 0; x < grids; x++) {
                JLabel item = new JLabel(new ImageIcon(ChessWindows.chess[y][x].getUrl()));
                if ((x + y) % 2 == 0) {
                    item.setBackground(theme.getB());
                } else {
                    item.setBackground(theme.getW());
                }
                ChessItem chessItem = new ChessItem(x, y, item);
                ChessWindows.chessItem.put(x + "-" + y, chessItem);
                item.setOpaque(true);
                main.add(item);
            }
        }

        main.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int X = e.getX() / 100;
                int Y = e.getY() / 95;
                if (clientPoint.x != null && clientPoint.y != null) {
                    if (X != clientPoint.x || Y != clientPoint.y) {
                        if (ChessWindows.selectLabel == null) {
                            Border lineBorder = BorderFactory.createLineBorder(Color.magenta, 10);
                            ChessItem chessItem = ChessWindows.chessItem.get(clientPoint.x + "-" + clientPoint.y);
                            JLabel item = chessItem.getjLabel();
                            item.setBorder(null);
                            clientPoint.x = X;
                            clientPoint.y = Y;
                            chessItem = ChessWindows.chessItem.get(X + "-" + Y);
                            item = chessItem.getjLabel();
                            item.setBorder(lineBorder);
                        }
                    }
                } else {
                    Border lineBorder = BorderFactory.createLineBorder(Color.magenta, 10);
                    ChessItem chessItem = ChessWindows.chessItem.get(X + "-" + Y);
                    JLabel item = chessItem.getjLabel();
                    clientPoint.x = X;
                    clientPoint.y = Y;
                    item.setBorder(lineBorder);
                }
            }
        });

        main.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int X = e.getX() / 100;
                int Y = e.getY() / 95;
                ChessItem chessItem = ChessWindows.chessItem.get(X + "-" + Y);
                JLabel item = chessItem.getjLabel();

                if (ChessWindows.selectLabel == null) {
                    if (ChessWindows.chess[Y][X].getType() == (count % 2) + 1) {
                        if (ChessWindows.chess[Y][X] == ChessType.Empty) {
                            return;
                        }
                        item.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                        ChessWindows.selectLabel = chessItem;
                        itemRule.rule(chessItem);
                    }
                } else {
                    move(X, Y, item);
                    ChessWindows.selectLabel.getjLabel().setBorder(null);
                    ChessWindows.selectLabel = null;
                }
            }
        });
        b = new JLabel("黑棋得分：" + bItem, SwingConstants.CENTER);
        b.setPreferredSize(new Dimension(100, 100));
        w = new JLabel("白棋得分：" + wItem, SwingConstants.CENTER);
        w.setPreferredSize(new Dimension(100, 100));


        if (count % 2 != 0) {
            countJl.setText("当前回合: " + ((count / 2) + 1));
        } else {
            countJl.setText("当前回合: " + ((count / 2)));
        }

        window.add(b, BorderLayout.WEST);
        window.add(w, BorderLayout.EAST);
        window.add(top, BorderLayout.NORTH);
        window.add(main);
        jf.setVisible(true);
        new Thread(new ThreadTimeOut()).start();
    }

    public static void move(Integer x, Integer y, JLabel l) {
        move(x, y, l, false);
    }

    public static void move(Integer x, Integer y, JLabel l, boolean flag) {
        Border border = l.getBorder();
        ChessType selectType;
        ChessType nowType;
        ClientPoint selectPoint;
        ChessItem selectLabelItem;
        boolean next;
        if (flag) {
            border = BorderFactory.createLineBorder(Color.red);
            selectLabelItem = ChessWindows.chessItem.get(1 + "-" + 1);
            selectPoint = selectLabelItem.getpoint();
            selectType = ChessWindows.chess[selectPoint.y][selectPoint.x];
            nowType = ChessWindows.chess[y][x];
            next = false;
        } else {
            selectLabelItem = ChessWindows.selectLabel;
            selectPoint = selectLabelItem.getpoint();
            selectType = ChessWindows.chess[selectPoint.y][selectPoint.x];
            nowType = ChessWindows.chess[y][x];
            //判断可走
            next = x.equals(selectPoint.x) && y.equals(selectPoint.y);
        }
        if (next) {

        } else if (border != null) {

            ChessCache chessCache = new ChessCache(ChessWindows.chess, count, bItem, wItem);
            ChessWindows.record.push(chessCache);

            if (!flag) {
                itemRule.remove(ChessWindows.selectLabel);
            }
            // 胜利
            if (nowType.equals(ChessType.Black_Queen) || nowType.equals(ChessType.White_Queen)) {
                BufferedInputStream isItem = null;
                Clip itemBgm = null;
                try {
                    itemBgm = AudioSystem.getClip();
                    isItem = new BufferedInputStream(new FileInputStream("media/success.wav"));
                    AudioInputStream items = AudioSystem.getAudioInputStream(isItem);
                    itemBgm.open(items);
                    itemBgm.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                } finally {
                    itemBgm = null;
                    if (isItem != null) {
                        try {
                            isItem.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                jf.dispose();
                String item;
                if (selectType.getType() == 1) {
                    item = "黑棋";
                } else {
                    item = "白棋";
                }
                new Info().draw(item + "获胜");
            }
            // 得分
            if (!nowType.equals(selectType) && !nowType.equals(ChessType.Empty)) {
                if (selectType.getType() == 1) {
                    bItem++;
                    Game.b.setText("黑棋得分：" + bItem);
                } else {
                    wItem++;
                    w.setText("白棋得分：" + wItem);
                }
            }
            // 更新地图
            ChessWindows.chess[selectPoint.y][selectPoint.x] = ChessType.Empty;
            ChessWindows.chess[y][x] = selectType;
            // 更新icon
            selectLabelItem.getjLabel().setIcon(null);
            l.setIcon(new ImageIcon(selectType.getUrl()));
            count++;
            String nowText;
            if ((count % 2) + 1 != 1) {
                nowText = "当前棋子: 白色";
            } else {
                nowText = "当前棋子: 黑色";
            }
            Game.nowType.setText(nowText);
            if (count % 2 != 0) {
                countJl.setText("当前回合: " + ((count / 2) + 1));
            }

            timeOut = System.currentTimeMillis() + (1000 * 30);
            BufferedInputStream isItem = null;
            Clip itemBgm = null;
            try {
                itemBgm = AudioSystem.getClip();
                isItem = new BufferedInputStream(new FileInputStream("media/item.wav"));
                AudioInputStream items = AudioSystem.getAudioInputStream(isItem);
                itemBgm.open(items);
                itemBgm.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                itemBgm = null;
                if (isItem != null) {
                    try {
                        isItem.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (!flag) {
            itemRule.remove(ChessWindows.selectLabel);
        }
    }
}
