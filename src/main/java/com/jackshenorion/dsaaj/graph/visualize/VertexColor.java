package com.jackshenorion.dsaaj.graph.visualize;

public enum VertexColor {
    WHITE(0, "whiteVertex"), GREY(1, "greyVertex"), BLACK(2, "greyVertex");

    private int code;
    private String styleName;

    VertexColor(int code, String styleName) {
        this.code = code;
        this.styleName = styleName;
    }

    public static VertexColor fromCode(int code) {
        switch (code) {
            case 0:
                return WHITE;
            case 1:
                return GREY;
            case 2:
                return BLACK;
            default:
                return WHITE;
        }
    }

    public static String getStyleName(int code) {
        return fromCode(code).getStyleName();
    }

    public int getCode() {
        return code;
    }

    public String getStyleName() {
        return styleName;
    }
}
