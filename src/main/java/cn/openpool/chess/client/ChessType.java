package cn.openpool.chess.client;

import java.io.Serializable;


public enum ChessType implements Serializable {
    /**
     * 类型
     */
    Black_Bishop("Black_Bishop.png", 13, 1),
    Black_King("Black_King.png", 15, 1),
    Black_Knight("Black_Knight.png", 12, 1),
    Black_Pawn("Black_Pawn.png", 16, 1),
    Black_Queen("Black_Queen.png", 14, 1),
    Black_Rook("Black_Rook.png", 11, 1),
    White_Bishop("White_Bishop.png", 23, 2),
    White_King("White_King.png", 25, 2),
    White_Knight("White_Knight.png", 22, 2),
    White_Pawn("White_Pawn.png", 26, 2),
    White_Queen("White_Queen.png", 24, 2),
    White_Rook("White_Rook.png", 21, 2),
    Empty("", 0, 0);

    private String url;
    private Integer code;
    private Integer type;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    ChessType(String url, Integer code, Integer type) {
        this.url = "img/" + url;
        this.code = code;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public Integer getCode() {
        return code;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ChessType{" +
                "url='" + url + '\'' +
                ", code=" + code +
                ", type=" + type +
                '}';
    }

}
