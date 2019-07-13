package pl.sda.jp.miniblogwa16.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import java.util.Collection;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private Collection<Integer> numbers;
   @JsonIgnore
    private String privateInfo;

}
