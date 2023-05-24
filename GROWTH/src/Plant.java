interface needsWater {
    void waterPlant();
}
abstract class Plant implements needsWater {
    private String id;
    private String commonName;
    private Integer potSize;
    private Float price;

    public Plant(String id, String commonName, Integer potSize, Float price) {
        this.id = id;
        this.commonName = commonName;
        this.potSize = potSize;
        this.price = price;
    }
    public String getCName() {
        return commonName;
    }
//    public String getAge() {
//        return age;
//    }
    public abstract void grow();
    @Override
    public void waterPlant() {
        System.out.printf("You need to water %s plant it's getting close to water time.", commonName);
    }
}
class Succulent extends Plant {
    public Succulent(String id,String commonName, Integer potSize, Float price) {
        super(id, commonName, potSize, price);
    }
    @Override
    public void grow(){
        //specific optional growing conditions for succulent
        System.out.println("Best option is to leave it alone and let it grow in spurts");
    }
    @Override
    public void waterPlant() {
        System.out.printf("Water succulent plant with special instructions and watch for these signs. Please dry out completely before watering ");
    }
}
class Foliage extends Plant {
//        private String leafShape;
//        private String leafColor;
    public Foliage(String id, String commonName, Integer potSize, Float price) {
        super(id, commonName, potSize, price);
//        this.leafShape = leafShape;
//        this.leafColor = leafColor;
    }
    public void checkRoots() {
        System.out.println("Please check my roots!");
    }
    @Override
    public void grow() {
        System.out.println("Foliage love humidity and love to bask in the sun!");
    }
    @Override
    public void waterPlant() {
        System.out.printf("Water foliage plant with special instructions. They need to be dried out before watering and they tend to love humid envroinments");
    }
}

