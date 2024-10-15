import io.restassured.response.Response;

public class Label {

    private static String labelID;
    private static String labelName;
    private static String labelColor;
    private static String labelNewName;
    private static String labelNewColor;

    public static String getLabelName() {
        return labelName;
    }
    public static void setLabelName(String labelName) {
        Label.labelName = labelName;
    }
    public static String getLabelColor() {
        return labelColor;
    }
    public static void setLabelColor(String labelColor) {
        Label.labelColor = labelColor;
    }
    public static String getLabelNewColor() {
        return labelNewColor;
    }
    public static void setLabelNewColor(String labelNewColor) {
        Label.labelNewColor = labelNewColor;
    }
    public static String getLabelNewName() {
        return labelNewName;
    }
    public static void setLabelNewName(String labelNewName) {
        Label.labelNewName = labelNewName;
    }
    public static String getLabelID() {
        return labelID;
    }

    public static Response create() {
        Response response = Base.request()
                .queryParam("name", labelName)
                .queryParam("color", labelColor)
                .queryParam("idBoard",Board.getBoardID())
                .when().post("labels");
        labelID = response.path("id");
        return response;
    }

    public static Response get() {
        return Base.request()
                .when().get("labels/" + labelID);
    }

    public static Response update() {
        return Base.request()
                .queryParams("name", labelNewName,
                        "color", labelNewColor)
                .when().put("labels/" + labelID);
    }

    public static Response delete() {
        return Base.request()
                .when().delete("labels/" + labelID);
    }

}