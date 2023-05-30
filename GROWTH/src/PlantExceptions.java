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
}
