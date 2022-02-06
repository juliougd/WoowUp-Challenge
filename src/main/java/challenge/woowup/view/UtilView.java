package challenge.woowup.view;

public class UtilView {

    public UtilView() {
    }

    static public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public enum Color {
        RESET("\033[0m"),
        RED("\033[1;31m");

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

}
