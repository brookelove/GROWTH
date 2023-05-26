
abstract class Plant {
    private String id;
    private String commonName;
    private Integer potSize;
    private Float price;
    private String varegationType;

    public Plant(String id, String commonName, Integer potSize, Float price, String varegationType) {
        this.id = id;
        this.commonName = commonName;
        this.potSize = potSize;
        this.price = price;
        this.varegationType = varegationType;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return commonName;
    }
    public abstract void grow();
}
class Succulent extends Plant {
    Boolean hasSpines;
    public Succulent(String id,String commonName, Integer potSize, Float price,String varegation, Boolean hasSpines) {
        super(id, commonName, potSize, price, varegation);
        this.hasSpines = hasSpines;
    }
    @Override
    public void grow(){
        System.out.printf("Your succulent %f grows best in these conditions:", getName());
    }
}
class Foliage extends Plant {
    public Foliage(String id, String commonName, Integer potSize, String vareigation, Float price) {
        super(id, commonName, potSize, price, vareigation);
    }
    public void pruneLeaves() {
        System.out.printf("Please check your leaves of the %s plant to make sure that there are no pre-dying leaves", getName());
    }
    @Override
    public void grow() {
        System.out.printf("Your foliage %f grows best in these conditions:", getName());
    }

}

