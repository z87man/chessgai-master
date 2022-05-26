package cn.openpool.chess.client;

import java.io.Serializable;


public class ClientPoint implements Serializable {
    public Integer x;
    public Integer y;

    @Override
    public String toString() {
        return "ClientPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public ClientPoint() {
        
    }

    public ClientPoint(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
