package buria.serhii.models.pieces;

import buria.serhii.models.CoordinateShift;

import java.util.HashSet;
import java.util.Set;

public interface IBishop {

    default Set<CoordinateShift> getBishopMoves () {
        Set<CoordinateShift> result = new HashSet<>();
        for (int i = -7; i <= 7 ; i++) {
            if (i == 0) continue;
            result.add(new CoordinateShift(i, i));
        }
        for (int i = -7; i < 7; i++) {
            if (i == 0) continue;
            result.add(new CoordinateShift(i, -i));
        }
        return result;
    }
}
