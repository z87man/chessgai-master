package cn.openpool.chess.client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class ItemRule {

    public void rule(ChessItem chessItem) {
        Border lineBorder = BorderFactory.createLineBorder(Color.red, 5);
        ClientPoint point = chessItem.getpoint();
        ChessType chess = ChessWindows.chess[point.y][point.x];
        switch (chess) {
            case Black_Knight:
            case White_Knight:
                knight(chessItem, chess, lineBorder);
                break;
            case Black_King:
            case White_King:
                bishop(chessItem, chess, lineBorder);
                rook(chessItem, lineBorder);
                break;
            case Black_Bishop:
            case White_Bishop:
                bishop(chessItem, chess, lineBorder);
                break;
            case Black_Queen:
            case White_Queen:
                queen(chessItem, chess, lineBorder);
                break;
            case Black_Pawn:
            case White_Pawn:
                pawn(chessItem, chess, lineBorder);
                break;
            case Black_Rook:
            case White_Rook:
                rook(chessItem, lineBorder);
                break;
            default:
                break;
        }
    }

    public void remove(ChessItem chessItem) {
        ClientPoint point = chessItem.getpoint();
        ChessType chess = ChessWindows.chess[point.y][point.x];
        switch (chess) {
            case Black_Knight:
            case White_Knight:
                knight(chessItem, chess, null);
                break;
            case Black_King:
            case White_King:
                bishop(chessItem, chess, null);
                rook(chessItem, null);
                break;
            case Black_Bishop:
            case White_Bishop:
                bishop(chessItem, chess, null);
                break;
            case Black_Queen:
            case White_Queen:
                queen(chessItem, chess, null);
            case Black_Pawn:
            case White_Pawn:
                pawn(chessItem, chess, null);
                break;
            case Black_Rook:
            case White_Rook:
                rook(chessItem, null);
                break;
            default:
                break;
        }
    }

    public void rook(ChessItem chessItem, Border border) {
        ClientPoint point = chessItem.getpoint();
        for (int i = point.x + 1; i < 8; i++) {
            if (ChessWindows.chess[point.y][i] == ChessType.Empty) {
                ChessItem item = ChessWindows.chessItem.get(i + "-" + point.y);
                item.getjLabel().setBorder(border);
            } else {
                if (!ChessWindows.chess[point.y][i].getType().equals(ChessWindows.chess[point.y][point.x].getType())) {
                    ChessItem item = ChessWindows.chessItem.get(i + "-" + point.y);
                    item.getjLabel().setBorder(border);
                }
                break;
            }
        }
        for (int i = point.x - 1; i > -1; i--) {
            if (ChessWindows.chess[point.y][i] == ChessType.Empty) {
                ChessItem item = ChessWindows.chessItem.get(i + "-" + point.y);
                item.getjLabel().setBorder(border);
            } else {
                if (!ChessWindows.chess[point.y][i].getType().equals(ChessWindows.chess[point.y][point.x].getType())) {
                    ChessItem item = ChessWindows.chessItem.get(i + "-" + point.y);
                    item.getjLabel().setBorder(border);
                }
                break;
            }
        }
        for (int i = point.y + 1; i < 8; i++) {
            if (ChessWindows.chess[i][point.x] == ChessType.Empty) {
                ChessItem item = ChessWindows.chessItem.get(point.x + "-" + i);
                item.getjLabel().setBorder(border);
            } else {
                if (!ChessWindows.chess[i][point.x].getType().equals(ChessWindows.chess[point.y][point.x].getType())) {
                    ChessItem item = ChessWindows.chessItem.get(point.x + "-" + i);
                    item.getjLabel().setBorder(border);
                }
                break;
            }
        }
        for (int i = point.y - 1; i > -1; i--) {
            if (ChessWindows.chess[i][point.x] == ChessType.Empty) {
                ChessItem item = ChessWindows.chessItem.get(point.x + "-" + i);
                item.getjLabel().setBorder(border);
            } else {
                if (!ChessWindows.chess[i][point.x].getType().equals(ChessWindows.chess[point.y][point.x].getType())) {
                    ChessItem item = ChessWindows.chessItem.get(point.x + "-" + i);
                    item.getjLabel().setBorder(border);
                }
                break;
            }
        }
    }

    public void pawn(ChessItem chessItem, ChessType chessType, Border border) {
        ClientPoint point = chessItem.getpoint();
        if (chessType.getType() == 1) {
            //吃子
            if (point.y < 7) {
                if (point.x < 7) {
                    ChessType chess2 = ChessWindows.chess[point.y + 1][point.x + 1];
                    if (!chess2.getType().equals(chessType.getType()) && chess2.getType() != 0) {
                        ChessWindows.chessItem.get((point.x + 1) + "-" + (point.y + 1)).getjLabel().setBorder(border);
                    }
                }
                if (point.x > 0) {
                    ChessType chess1 = ChessWindows.chess[point.y + 1][point.x - 1];
                    if (!chess1.getType().equals(chessType.getType()) && chess1.getType() != 0) {
                        ChessWindows.chessItem.get((point.x - 1) + "-" + (point.y + 1)).getjLabel().setBorder(border);
                    }
                }


            }
            //前进
            if (point.y == 1) {
                if (ChessWindows.chess[point.y + 1][point.x] == ChessType.Empty) {
                    ChessWindows.chessItem.get((point.x) + "-" + (point.y + 1)).getjLabel().setBorder(border);
                }
                if (ChessWindows.chess[point.y + 2][point.x] == ChessType.Empty) {
                    ChessWindows.chessItem.get((point.x) + "-" + (point.y + 2)).getjLabel().setBorder(border);
                }
            } else {
                if (point.y < 7) {
                    if (ChessWindows.chess[point.y + 1][point.x] == ChessType.Empty) {
                        ChessWindows.chessItem.get((point.x) + "-" + (point.y + 1)).getjLabel().setBorder(border);
                    }
                }
            }
        } else {
            if (chessType.getType() == 2) {
                //吃子
                if (point.y > 0) {
                    if (point.x < 7) {
                        ChessType chess2 = ChessWindows.chess[point.y - 1][point.x + 1];
                        if (!chess2.getType().equals(chessType.getType()) && chess2.getType() != 0) {
                            ChessWindows.chessItem.get((point.x + 1) + "-" + (point.y - 1)).getjLabel().setBorder(border);
                        }
                    }
                    if (point.x > 0) {
                        ChessType chess1 = ChessWindows.chess[point.y - 1][point.x - 1];
                        if (!chess1.getType().equals(chessType.getType()) && chess1.getType() != 0) {
                            ChessWindows.chessItem.get((point.x - 1) + "-" + (point.y - 1)).getjLabel().setBorder(border);
                        }
                    }
                }
                //前进
                if (point.y == 6) {
                    if (ChessWindows.chess[point.y - 1][point.x] == ChessType.Empty) {
                        ChessWindows.chessItem.get((point.x) + "-" + (point.y - 1)).getjLabel().setBorder(border);
                    }
                    if (ChessWindows.chess[point.y - 2][point.x] == ChessType.Empty) {
                        ChessWindows.chessItem.get((point.x) + "-" + (point.y - 2)).getjLabel().setBorder(border);
                    }
                } else {
                    if (point.y > 0) {
                        if (ChessWindows.chess[point.y - 1][point.x] == ChessType.Empty) {
                            ChessWindows.chessItem.get((point.x) + "-" + (point.y - 1)).getjLabel().setBorder(border);
                        }

                    }
                }
            }
        }
    }

    public void queen(ChessItem chessItem, ChessType chessType, Border border) {
        ClientPoint point = chessItem.getpoint();
        if (point.y > 0) {
            ChessType chessT = ChessWindows.chess[point.y - 1][point.x];
            if (point.x > 0) {
                ChessType chessTL = ChessWindows.chess[point.y - 1][point.x - 1];
                if (!chessType.getType().equals(chessTL.getType())) {
                    ChessWindows.chessItem.get((point.x - 1) + "-" + (point.y - 1)).getjLabel().setBorder(border);
                }
            }

            if (!chessType.getType().equals(chessT.getType())) {
                ChessWindows.chessItem.get((point.x) + "-" + (point.y - 1)).getjLabel().setBorder(border);
            }
        }
        if (point.y < 7) {
            ChessType chessB = ChessWindows.chess[point.y + 1][point.x];
            if (point.x < 7) {
                ChessType chessBR = ChessWindows.chess[point.y + 1][point.x + 1];
                if (!chessType.getType().equals(chessBR.getType())) {
                    ChessWindows.chessItem.get((point.x + 1) + "-" + (point.y + 1)).getjLabel().setBorder(border);
                }
            }
            if (!chessType.getType().equals(chessB.getType())) {
                ChessWindows.chessItem.get((point.x) + "-" + (point.y + 1)).getjLabel().setBorder(border);
            }
        }
        if (point.x > 0) {
            ChessType chessL = ChessWindows.chess[point.y][point.x - 1];
            if (!chessType.getType().equals(chessL.getType())) {
                ChessWindows.chessItem.get((point.x - 1) + "-" + (point.y)).getjLabel().setBorder(border);
            }
            if (point.y < 7) {
                ChessType chessLB = ChessWindows.chess[point.y + 1][point.x - 1];
                if (!chessType.getType().equals(chessLB.getType())) {
                    ChessWindows.chessItem.get((point.x - 1) + "-" + (point.y + 1)).getjLabel().setBorder(border);
                }
            }
        }
        if (point.x < 7) {
            ChessType chessR = ChessWindows.chess[point.y][point.x + 1];
            if (!chessType.getType().equals(chessR.getType())) {
                ChessWindows.chessItem.get((point.x + 1) + "-" + (point.y)).getjLabel().setBorder(border);
            }
            if (point.y > 0) {
                ChessType chessRT = ChessWindows.chess[point.y - 1][point.x + 1];
                if (!chessType.getType().equals(chessRT.getType())) {
                    ChessWindows.chessItem.get((point.x + 1) + "-" + (point.y - 1)).getjLabel().setBorder(border);
                }
            }
        }
    }

    public void bishop(ChessItem chessItem, ChessType chessType, Border border) {
        ClientPoint point = chessItem.getpoint();
        int count = point.x + 1;
        for (int i = point.y - 1; i > -1; i--) {
            if (count < 8) {
                ChessType chessL = ChessWindows.chess[i][count];
                if (chessL.equals(ChessType.Empty)) {
                    ChessWindows.chessItem.get((count) + "-" + i).getjLabel().setBorder(border);
                } else {
                    if (!chessL.getType().equals(chessType.getType())) {
                        ChessWindows.chessItem.get((count) + "-" + i).getjLabel().setBorder(border);
                    }
                    break;
                }
            }
            count++;
        }
        count = point.x - 1;
        for (int i = point.y - 1; i > -1; i--) {
            if (count > -1) {
                ChessType chessL = ChessWindows.chess[i][count];
                if (chessL.equals(ChessType.Empty)) {
                    ChessWindows.chessItem.get((count) + "-" + i).getjLabel().setBorder(border);
                } else {
                    if (!chessL.getType().equals(chessType.getType())) {
                        ChessWindows.chessItem.get((count) + "-" + i).getjLabel().setBorder(border);
                    }
                    break;
                }
            }
            count--;
        }
        count = point.x + 1;
        for (int i = point.y + 1; i < 8; i++) {
            if (count < 8) {
                ChessType chessR = ChessWindows.chess[i][count];
                if (chessR.equals(ChessType.Empty)) {
                    ChessWindows.chessItem.get((count) + "-" + i).getjLabel().setBorder(border);
                } else {
                    if (!chessR.getType().equals(chessType.getType())) {
                        ChessWindows.chessItem.get((count) + "-" + i).getjLabel().setBorder(border);
                    }
                    break;
                }
            }
            count++;
        }
        count = point.x - 1;
        for (int i = point.y + 1; i < 8; i++) {
            if (count > -1) {
                ChessType chessL = ChessWindows.chess[i][count];
                if (chessL.equals(ChessType.Empty)) {
                    ChessWindows.chessItem.get((count) + "-" + i).getjLabel().setBorder(border);
                } else {
                    if (!chessL.getType().equals(chessType.getType())) {
                        ChessWindows.chessItem.get((count) + "-" + i).getjLabel().setBorder(border);
                    }
                    break;
                }
            }
            count--;
        }
    }

    public void knight(ChessItem chessItem, ChessType chessType, Border border) {
        ClientPoint point = chessItem.getpoint();
        Integer reX = point.x;
        Integer reY = point.y;
        if (reX > 1) {
            if (reY >= 1) {
                ChessType chessLT = ChessWindows.chess[reY - 1][reX - 2];
                if (!chessLT.getType().equals(chessType.getType())) {
                    ChessWindows.chessItem.get((reX - 2) + "-" + (reY - 1)).getjLabel().setBorder(border);
                }
            }
            if (reY <= 6) {
                ChessType chessLB = ChessWindows.chess[reY + 1][reX - 2];
                if (!chessLB.getType().equals(chessType.getType())) {
                    ChessWindows.chessItem.get((reX - 2) + "-" + (reY + 1)).getjLabel().setBorder(border);
                }
            }
        }
        if (reX < 6) {
            if (reY >= 1) {
                ChessType chessRT = ChessWindows.chess[reY - 1][reX + 2];
                if (!chessRT.getType().equals(chessType.getType())) {
                    ChessWindows.chessItem.get((reX + 2) + "-" + (reY - 1)).getjLabel().setBorder(border);
                }
            }
            if (reY <= 6) {
                ChessType chessRB = ChessWindows.chess[reY + 1][reX + 2];
                if (!chessRB.getType().equals(chessType.getType())) {
                    ChessWindows.chessItem.get((reX + 2) + "-" + (reY + 1)).getjLabel().setBorder(border);
                }
            }
        }
        if (reY > 1) {
            if (reX >= 1) {
                ChessType chessTL = ChessWindows.chess[reY - 2][reX - 1];
                if (!chessTL.getType().equals(chessType.getType())) {
                    ChessWindows.chessItem.get((reX - 1) + "-" + (reY - 2)).getjLabel().setBorder(border);
                }
            }
            if (reX <= 6) {
                ChessType chessTR = ChessWindows.chess[reY - 2][reX + 1];
                if (!chessTR.getType().equals(chessType.getType())) {
                    ChessWindows.chessItem.get((reX + 1) + "-" + (reY - 2)).getjLabel().setBorder(border);
                }
            }
        }
        if (reY < 6) {
            if (reX >= 1) {
                ChessType chessBL = ChessWindows.chess[reY + 2][reX - 1];
                if (!chessBL.getType().equals(chessType.getType())) {
                    ChessWindows.chessItem.get((reX - 1) + "-" + (reY + 2)).getjLabel().setBorder(border);
                }
            }
            if (reX <= 6) {
                ChessType chessBR = ChessWindows.chess[reY + 2][reX + 1];
                if (!chessBR.getType().equals(chessType.getType())) {
                    ChessWindows.chessItem.get((reX + 1) + "-" + (reY + 2)).getjLabel().setBorder(border);
                }

            }
        }
    }
}
