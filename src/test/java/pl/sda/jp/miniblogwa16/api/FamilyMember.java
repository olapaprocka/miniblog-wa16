package pl.sda.jp.miniblogwa16.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//pomocniczy obiekty któym będziemy chcieli do JSON
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FamilyMember {
private  FamilyMemberType type;
private String name;



}
