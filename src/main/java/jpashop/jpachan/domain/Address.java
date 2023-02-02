package jpashop.jpachan.domain;

public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address(){

    }

    public Address(String city,String Street,String zipcode){
        this.city = city;
        this.street=Street;
        this.zipcode=zipcode;
    }
}
