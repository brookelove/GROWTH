public class PlantExceptions extends Exception {
//    public PlantExceptions(String errorMessage) {
//        super(errorMessage);
////        System.out.println("There is no plant with .");
//    }
    public static class NoPlantFound extends Exception{
        public NoPlantFound(String message) {
            super(message);
        }
    }
    public static class NotAChar extends Exception {
        public NotAChar(String message) {
            super(message);
        }
    }
    public static class NotAValidNum extends Exception {
        public NotAValidNum(String message){
            super(message);
        }
    }
}
