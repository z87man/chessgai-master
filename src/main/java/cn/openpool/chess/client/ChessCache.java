package cn.openpool.chess.client;

import javax.swing.*;
import java.io.Serializable;
import java.util.Arrays;


public class ChessCache implements Serializable {
    private ChessType[][] chess = new ChessType[8][8];
    private Integer count;
    private Integer bItem;
    private Integer wItem;

    public ChessType[][] getChess() {
        return chess;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getbItem() {
        return bItem;
    }

    public Integer getwItem() {
        return wItem;
    }

    public ChessCache(ChessType[][] chess, Integer count, Integer bItem, Integer wItem) {
        for (int i = 0; i < chess.length; i++) {
            System.arraycopy(chess[i], 0, this.chess[i], 0, chess[i].length);
        }
        this.count = count;
        this.bItem = bItem;
        this.wItem = wItem;
    }

    public void refresh() {
        for (int i = 0; i < chess.length; i++) {
            System.arraycopy(chess[i], 0, ChessWindows.chess[i], 0, chess[i].length);
        }
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                ChessWindows.chessItem.get(x + "-" + y).getjLabel().setIcon(new ImageIcon(ChessWindows.chess[y][x].getUrl()));
            }
        }
        Game.count = count;
        Game.bItem = bItem;
        Game.wItem = wItem;
        Game.b.setText("黑棋得分：" + Game.bItem);
        Game.w.setText("白棋得分：" + Game.wItem);
        String nowText;
        if ((Game.count % 2) + 1 != 1) {
            nowText = "当前棋子: 白色";
        } else {
            nowText = "当前棋子: 黑色";
        }
        Game.nowType.setText(nowText);
        if (Game.count % 2 != 0) {
            Game.countJl.setText("当前回合: " + ((Game.count / 2) + 1));
        } else {
            Game.countJl.setText("当前回合: " + ((Game.count / 2)));
        }
    }
}
