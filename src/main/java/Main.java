import Logic.Controller;
import Logic.Builder.Director;

public class Main {
    public static void main( String[] args) {
        new Thread(
                new Controller(
                        new Director()
                                .make()))
                .start();
    }
}
