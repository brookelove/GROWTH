public class PlantExceptions extends Exception {

    public static class NoPlantFound extends Exception{
       /*Custom Exception to catch if there is no plant found within GROWTH or if there is no plant added to the character*/
        public NoPlantFound(String message) {
            super(message);
        }
    }
    /*Custom Exception to catch if it is not a character in trying to find the character*/
    public static class NotAChar extends Exception {
        public NotAChar(String message) {
            super(message);
        }
    }
}
