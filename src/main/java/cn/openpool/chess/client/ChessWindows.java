package cn.openpool.chess.client;

import javax.swing.*;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;


public class ChessWindows {

    public static ChessItem selectLabel;
    /**
     * 记录 每个点位的 JLabel
     */
    public static ConcurrentHashMap<String, ChessItem> chessItem = new ConcurrentHashMap<>();

    public static Stack<ChessCache> record = new Stack<>();

    /**
     * 棋盘
     */
    public static ChessType[][] chess = new ChessType[][]{
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

    public static class NowSelect {
        public JLabel jLabel;
        public Integer x;
        public Integer y;

        public NowSelect(JLabel jLabel, Integer x, Integer y) {
            this.jLabel = jLabel;
            this.x = x;
            this.y = y;
        }
    }
}
