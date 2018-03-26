package com.jackshenorion.dsaaj.circle;

import java.util.ArrayList;
import java.util.List;

public class CircleCoordinateProvider {

    public static List<Coordinate> getCoordinates(int vertexCount, int minimumWidth) {
        double angel = 360.0 / vertexCount;
        double perimeter = vertexCount * minimumWidth * 3;
        double r = perimeter / (Math.PI * 2);
        double origx = r;
        double origy = origx;

        List<Coordinate> coordinates = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i ++) {
            double thisAngle = angel * i;
            coordinates.add(new Coordinate(origx + r * Math.cos(Math.toRadians(thisAngle)), origy + r * Math.sin(Math.toRadians(thisAngle))));
        }
        return coordinates;
    }
}
