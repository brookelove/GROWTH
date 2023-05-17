interface needsWater {
    void waterPlant();
}
abstract class Plant implements needsWater {
    // holds all common attributes with plants [leaves, varrigation, light,
    // water]
    // have to create an ID for each plant
    private String commonName;
    private String age;
    // we will be providing the best lightSource information as well as other information
    private Boolean hasVarrigation;
    private Integer potSize;
    private Float price;

    public Plant(String commonName, String age, Boolean hasVarrigation, Integer potSize, Float price) {
        this.commonName = commonName;
        this.age = age;
        this.hasVarrigation = hasVarrigation;
        this.potSize = potSize;
        this.price = price;
    }
    public String getCName() {
        return commonName;
    }
    public String getAgee() {
        return age;
    }
    public Boolean getVarrigation() {
        return hasVarrigation;
    }
//probably use care instructions here for it
    public abstract void grow();
    public abstract void varrigationInfo();
    @Override
    public void waterPlant() {
        System.out.printf("You need to water %s plant it's getting close to water time.", commonName);
    }
}
class Succulent extends Plant {
    private Boolean hasSpikes;
    private Boolean hasBloom;
    public Succulent(String commonName, String age, Boolean hasVarrigation, Integer potSize, Float price, Boolean hasSpikes, Boolean hasBool) {
        super(commonName,age,hasVarrigation, potSize, price);
        this.hasBloom = hasBloom;
        this.hasSpikes = hasSpikes;
    }
    @Override
    public void grow(){
        //specific optional growing conditions for succulent
        System.out.println("Best option is to leave it alone and let it grow in spurts");
    }
    @Override
    public void varrigationInfo(){
        //specific optional growing conditions for succulennt
        System.out.println("Congrats on your varrigation of your succulent!");
    }
    @Override
    public void waterPlant() {
        System.out.printf("Water succulent plant with special instructions and watch for these signs. Please dry out completely before watering ");
    }
}
class Foliage extends Plant {
        private String leafShape;
        private String leafColor;
    public Foliage(String commonName, String age, Boolean hasVarrigation, Integer potSize, Float price, String leafShape, String leafColor) {
        super(commonName,age,hasVarrigation, potSize, price);
        this.leafShape = leafShape;
        this.leafColor = leafColor;
    }
    public void checkRoots() {
        System.out.println("Please check my roots!");
    }
    @Override
    public void grow() {
        System.out.println("Foliage love humidity and love to bask in the sun!");
    }
    @Override
    public void varrigationInfo(){
        //specific optional growing conditions for foliage
        System.out.println("Congrats on your varrigation of your foliage!");
    }
    @Override
    public void waterPlant() {
        System.out.printf("Water foliage plant with special instructions. They need to be dried out before watering and they tend to love humid envroinments");
    }
}
// Don't know the plants common name so need to ask questions about need to take in and make a connection
//    abstract class Mystery extends Plant {
//        /// if the plant is a mystery has to ask questions based on color look and ect.
//        /// to make work
//        @Override
//        public void waterPlants() {
//            // different kind of watering type than the
//        }
//
//        @Override
//        public void grow() {
//
//        }
//
//    }
//
//}
