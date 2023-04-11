package com.example.ex3.controller;


import com.example.ex3.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//여기는 CONTROLLER

@Controller // 어노테이션을 해줘야 이곳이 controller라는 의미
@RequestMapping({"/sample"})// 요청하는 가상 directory
@Log4j2 //롬복의 기능. 스프링부트가 로그라이브러리중에 log4j2를 기본으로 사용하고있음.
public class SampleController {
    @GetMapping("/ex1") //가상디렉토리 지정. requestMapping의 주소를 받아와 하위디렉토리를 호출

    public void ex1(){
        log.info("ex1.............");
    }
    @GetMapping({"/ex2","/exLink","/exFormat","fragment"}) // 매핑을 다양하게하면, 어떤 곳에서도 exModel이 똑같이 호출됨.
    //localhost:8080/sample/ex2 ...
    public void exModel(Model model){
        List<SampleDTO> list = IntStream.rangeClosed(1,20).asLongStream().mapToObj(i->{ //29번줄이 38번줄에서 사용됨.
            SampleDTO dto = SampleDTO.builder() //DTOP 객체를 builder로 생성하고 ,각 데이터를 받아와 dto객체를 반환한다.
                    .sno(i)
                    .first("First.."+i)
                    .last("Last.."+i)
                    .regTime(LocalDateTime.now())
                    .build();
            return dto;
        }).collect(Collectors.toList()); // list에 받아온 정보를 저장한다.
        model.addAttribute("list", list); // 총 20개의 sampleDTO를 생성한다.
//      --> SampleDTO(sno=1, first=First..1, last=Last..1, regTime=2023-04-11T13:58:13.216932600)  * 20
    }
    @GetMapping({"/exInline"}) // /sample/exInline을 호출하면, 해당하는 dto값을 속성으로 갖고 ex3로 리다이렉트한다.
    public String exInline(RedirectAttributes redirectAttributes){
        log.info("exInline을 호출함.............");

        SampleDTO dto = SampleDTO.builder()
                .sno(100L)
                .first("First...100")
                .last("Last...100")
                .regTime(LocalDateTime.now())
                .build();
        redirectAttributes.addFlashAttribute("result","어 성공이야");
        redirectAttributes.addFlashAttribute("dto",dto);
        return "redirect:/sample/ex3";
    }
    @GetMapping("/ex3")
    public void ex3(){
        log.info("ex가 호출되었음.");
    }
    @GetMapping({"/exLayout1","/exLayout2","/exTemplate"})
    public void exLayout1(){
        log.info("exLayout1 .......called");
    }
}
