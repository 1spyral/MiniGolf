// Class with static methods for converting angles between degrees and radians
public class AngleConverter {
    static double pi = Math.PI;
    private AngleConverter() {}
    // DEG to RAD
    public static double radians(double degrees) {
        return degrees * pi / 180;
    }
    // RAD to DEG
    public static double degrees(double radians) {
        return (radians * 180 / pi) % 360;
    }
}
