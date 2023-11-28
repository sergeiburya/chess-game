package buria.serhii.models.pieces;

public enum File {
    A, B, C, D, E, F, G, H;

    public static File fromChar(char ch) {
        try {
            return File.valueOf(String.valueOf(ch).toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
