package jpashop.jpachan.controller;

import jakarta.validation.Valid;
import jpashop.jpachan.domain.Address;
import jpashop.jpachan.domain.Member;
import jpashop.jpachan.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(),form.getStreet(),form.getZipcode());
        Member member = new Member();

        member.setName(form.getName());
        member.setAddress(address);
        member.setPassword(form.getPassword());
        member.setEmail(form.getEmail());
        member.setNickname(form.getNickname());
        member.setUserid(form.getUserid());
        memberService.join(member);
        return  "redirect:/";
    }

}
