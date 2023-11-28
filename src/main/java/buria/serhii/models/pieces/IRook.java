package buria.serhii.models.pieces;

import buria.serhii.models.CoordinateShift;

import java.util.HashSet;
import java.util.Set;

public interface IRook {

    default Set<CoordinateShift> getRookMoves () {
        Set<CoordinateShift> result = new HashSet<>();
        for (int i = -7; i <= 7 ; i++) {
            if (i == 0) continue;
            result.add(new CoordinateShift(i, 0));
        }
        for (int i = -7; i < 7; i++) {
            if (i == 0) continue;
            result.add(new CoordinateShift(0, i));
        }
        return result;
    }
}
