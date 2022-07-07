package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

public class ContactDto {
    int id;
    String name;
    String lastName;
    String email;
    String phone;
    String address;
    String description;

}
