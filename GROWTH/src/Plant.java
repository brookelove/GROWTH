
abstract class Plant {
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
    public String getId() {
        return id;
    }
    public String getName() {
        return commonName;
    }
    public abstract void grow();
}
class Succulent extends Plant {
    public Succulent(String id,String commonName, Integer potSize, Float price) {
        super(id, commonName, potSize, price);
    }
    @Override
    public void grow(){
        System.out.printf("Your succulent %f grows best in these conditions:", getName());
    }
}
class Foliage extends Plant {
    public Foliage(String id, String commonName, Integer potSize, Float price) {
        super(id, commonName, potSize, price);
    }
    @Override
    public void grow() {
        System.out.printf("Your foliage %f grows best in these conditions:", getName());
    }

}

