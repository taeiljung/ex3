package com.example.ex3.controller;


import com.example.ex3.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller // 어노테이션을 해줘야 이곳이 controller라는 의미
@RequestMapping("/sample")// 요청하는 가상 directory
@Log4j2 //롬복의 기능. 스프링부트가 로그라이브러리중에 log4j2를 기본으로 사용하고있음.
public class SampleController {
    @GetMapping("/ex1") //가상디렉토리 지정.
    public void ex1(){
        log.info("ex1.............");
    }
    @GetMapping("/ex2")
    public void exModel(Model model){
        List<SampleDTO> list = IntStream.rangeClosed(1,20).asLongStream().mapToObj(i->{
            SampleDTO dto = SampleDTO.builder()
                    .sno(i)
                    .first("First.."+i)
                    .last("Last.."+i)
                    .regTime(LocalDateTime.now())
                    .build();
            return dto;
        }).collect(Collectors.toList());
        model.addAttribute("list", list);
    }
}
