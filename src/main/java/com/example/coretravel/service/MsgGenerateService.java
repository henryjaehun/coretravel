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
    public Guide getGuideInfoByName(String guideName) {
        // 레포지토리에서 이름으로정보조회함

        // 없을 경우 예외처리
        return guideRepository.findByGuidename(guideName)
                .orElseThrow(() -> new RuntimeException("Guide not found for name: " + guideName));

    }

    // 3. 실질적으로 메시지의 폼을 생성한다
    private String createMessage(String clientName, String tourDate,
                               String pickupLocation, String pickupTime, Guide guide){
        // 내용 생성 필요
        return String.format("""
                Dear. %s
                Warm greetings from Core Travel!
                We are happy to have you for the Day tour! Below is the information.
                
                Tour: UNESCO Day Tour West Course
                Date: %s
                Pick-up Time: %s (Please be ready 5 minutes earlier)
                ※ The bus/van won’t wait for your tardiness of arrival.
                Pick-up Place: %s
                (Please wait outside the hotel's main entrance)
                Guide Name: %s
                Guide Contact: %s
                Vehicle: %s
                
                ※ You can contact our guide directly on the tour day should you have any inquiries via WhatsApp link below:
                https://api.whatsapp.com/send?phone=%s&text=
                ※ Please make sure to have appropriate footwear for the hike.
                ※ Please reply to us once you check this for confirmation.
                ※ Tour course is subject to change according to local circumstances such as seasonal events/weather/road conditions/etc.
                
                Best regards,
                %s
                """,
                clientName,
                tourDate,
                pickupTime,
                pickupLocation,
                guide.getGuidename(),
                guide.getPhone(),
                guide.getVehicle(),
                guide.getPhone(),
                guide.getGuidename());
    }


}
