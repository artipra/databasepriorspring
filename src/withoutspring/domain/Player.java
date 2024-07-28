package withoutspring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;

}
