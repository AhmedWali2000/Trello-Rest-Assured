import io.restassured.response.Response;

public class Board {
    private static String boardID;
    private static String boardName;
    private static String newBoardName;

    public static void setBoardName(String boardName) {
        Board.boardName = boardName;
    }
    public static void setNewBoardName(String newBoardName) {
        Board.newBoardName = newBoardName;
    }
    public static String getBoardName() {
        return boardName;
    }
    public static String getNewBoardName() {
        return newBoardName;
    }
    public static void setBoardID(String boardID) {
        Board.boardID = boardID;
    }
    public static String getBoardID() {
        return boardID;
    }

    public static Response  create() {
         Response response = Base.request()
                .queryParam("name", boardName)
                .when().post("boards");
        boardID = response.jsonPath().getString("id");
        return response;
    }


    public static Response get() {
        return Base.request()
                .when().get("boards/" + boardID);
    }

    public static Response update() {
        return Base.request()
                .queryParam("name", newBoardName)
                .when().put("boards/" + boardID);
    }

    public static Response delete() {
        return Base.request()
                .when().delete("boards/" + boardID);
    }

}
