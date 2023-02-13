package jpashop.jpachan.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이름을 써야합니다.")
    private String name;
    @NotEmpty(message = "아이디를 써야합니다.")
    private String userid;
    @NotEmpty(message = "닉네임을 써야합니다.")
    private String nickname;
    @NotEmpty(message = "비밀번호를 써야합니다.")
    private String password;
    @NotEmpty(message = "이메일을 써야합니다.")
    private String email;
    @NotEmpty(message = "주소를 써야합니다.")
    private String city;
    @NotEmpty(message = "주소를 써야합니다.")
    private String street;
    @NotEmpty(message = "우편번호를 써야합니다.")
    private String zipcode;
}
