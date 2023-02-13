package jpashop.jpachan.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;

@Embeddable
@Getter
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

