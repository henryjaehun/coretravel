package com.example.coretravel.service;

import com.example.coretravel.entity.Guide;
import com.example.coretravel.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MsgGenerateService {

    @Autowired
    GuideRepository guideRepository;



    // 1. 메시지 뼈대를 생성하는 하는 메소드, 가이드 정보를 조회하고 생성된 메시지를 조회하는걸 아우른다
    // 메소드 인자로 (손님이름,투어일시, 픽업호텔, 픽업시간, 가이드이름 )를 받아야함
    // 이름으로가이드조회메소드로 호출한 정보를 가이드 인스턴스를 생성하여 입력함
    public String generateMessage(String clientName, String tourDate,
                                  String pickupLocation, String pickupTime,
                                  String guideName) {

        Guide guide = getGuideInfoByName(guideName);

        return createMessage(clientName, tourDate, pickupTime, pickupLocation, guide);

    }


    // 2. 가이드를 이름으로 조회하여 데이터베이스에서 가져오는 메소드
    public String getGuideInfoByName(String guideName) {
        // 레포지토리에서 이름으로정보조회함

        // 없을 경우 예외처리
        return guideRepository.findByName(guideName)
                .orElseThrow(() -> new RuntimeException("Guide not found for name: " + guideName))
                .toString();

    }

    // 3. 실질적으로 메시지의 폼을 생성한다
    private String createMessage(String clientName, String tourDate,
                               String pickupLocation, String pickupTime, Guide guide){
        // 내용 생성 필요
        return null;
    }


}
