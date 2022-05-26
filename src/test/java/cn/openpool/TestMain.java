package cn.openpool;

import cn.openpool.chess.client.ChessType;
import cn.openpool.chess.client.SaveItem;
import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author 王居三木超
 * @version 1.0
 * @date 2022/5/26 22:46
 * @description TODO
 **/
public class TestMain {
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
                    ChessType.White_Pawn,
                    ChessType.White_Pawn,
                    ChessType.White_Pawn,
                    ChessType.White_Pawn,
                    ChessType.White_Pawn,
                    ChessType.White_Pawn,
                    ChessType.White_Pawn,
                    ChessType.White_Pawn,
            }
    };

    @Test
    public void test() {
        File file = null;
        try {
            file = new File("103.ch");
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
            }
            SaveItem saveItem = new SaveItem(null, null, null, chess, null);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(saveItem);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
