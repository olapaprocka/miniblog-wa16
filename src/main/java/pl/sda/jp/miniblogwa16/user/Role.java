package pl.sda.jp.miniblogwa16.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(unique = true)
    private String roleName;

    @Override
    public String toString() {
        return roleName;
    }

    public Role(String roleName) {

        this.roleName = roleName;
    }

}
