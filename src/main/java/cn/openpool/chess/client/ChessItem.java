package cn.openpool.chess.client;

import javax.swing.*;


public class ChessItem {

    private JLabel jLabel;
    private ClientPoint point;


    public ChessItem(Integer x, Integer y, JLabel jLabel) {
        this.jLabel = jLabel;
        this.point = new ClientPoint(x, y);
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public ClientPoint getpoint() {
        return point;
    }

}
