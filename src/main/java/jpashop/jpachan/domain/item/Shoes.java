package jpashop.jpachan.domain.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("S")
@Getter
@Setter
public class Shoes extends Item{
    private String brand;
    private String model_name;
    private String size;
}
