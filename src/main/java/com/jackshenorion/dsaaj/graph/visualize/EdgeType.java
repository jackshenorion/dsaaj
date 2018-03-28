package com.jackshenorion.dsaaj.graph.visualize;

public enum EdgeType {
    DEFAULT(0, "defEdge"),ON_TREE(1, "boldEdge");

    private int code;
    private String styleName;

    EdgeType(int code, String styleName) {
        this.code = code;
        this.styleName = styleName;
    }

    public static EdgeType fromCode(int code) {
        switch (code) {
            case 0:
                return DEFAULT;
            case 1:
                return ON_TREE;
            default:
                return DEFAULT;
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
