package com.example.coretravel.controller;

import com.example.coretravel.service.MsgGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MsgGenerateController {
    //서비스클래스의 의존성을 주입한다
    @Autowired
    MsgGenerateService msgGenerateService;

    //겟 요청으로 서비스레이어의 generateMSG메서드 호, 입력인자로 다섯가지 받음
    //그후 리턴은 서비스클래스의 메소드로
    public String generateMessage(
            @RequestParam String clientName,
            @RequestParam String tourDate,
            @RequestParam String pickupTime,
            @RequestParam String pickupLocation,
            @RequestParam String guideName) {

        return msgGenerateService.generateMessage(clientName, tourDate,
                pickupTime, pickupLocation, guideName);

    }

}
