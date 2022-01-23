public class AngleConverter {
    static double pi = Math.PI;
    private AngleConverter() {}
    public static double radians(double degrees) {
        return degrees * pi / 180;
    }
    public static double degrees(double radians) {
        return (radians * 180 / pi) % 360;
    }
}
