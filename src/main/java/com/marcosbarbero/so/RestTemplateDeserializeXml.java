package com.marcosbarbero.so;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

/**
 * https://stackoverflow.com/questions/60772079/spring-resttemplate-deserialize-xml-to-object-return-null
 */
@RestController
public class RestTemplateDeserializeXml {

    String response = """
            <ArrayOfReserveInfo xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://tempuri.org/">
              <random>12345</random>
              <ReserveInfo>
                <reserveId>8095727</reserveId>
                <bookingNo>00232003310080957272</bookingNo>
                <performanceId>124174</performanceId>
                <performance>performance</performance>
                <placeKr>CK</placeKr>
                <hallKr>hall</hallKr>
                <playYMD>20200331</playYMD>
                <startHM>1300</startHM>
                <dayName>2</dayName>
                <playNum>2</playNum>
                <scheCd>380926</scheCd>
                <reserveCnt>1</reserveCnt>
                <reserveUser>whoami</reserveUser>
                <pinCode>21</pinCode>
                <statusCd>02</statusCd>
                <statusNm>done</statusNm>
                <ticketAmt>1000</ticketAmt>
                <discountNm/>
                <printYN>N</printYN>
                <payAmt>1000</payAmt>
                <typeCd>99</typeCd>
                <typeName>normal</typeName>
                <reserveUserId>youcantseeme</reserveUserId>
                <reserveDate/>
              </ReserveInfo>
            </ArrayOfReserveInfo>
            """;

    String generated = """
            <?xml version="1.0" encoding="UTF-8"?>
            <UserReservationDto>
               <reserveInfo>
                  <reserveId />
                  <bookingNo>1234</bookingNo>
                  <performanceId />
                  <performance />
                  <placeKr />
                  <hallKr>aaaa</hallKr>
                  <playYMD />
                  <startHM />
                  <dayName>Monday</dayName>
                  <playNum />
                  <scheCd />
                  <reserveCnt />
                  <reserveUser />
                  <pinCode />
                  <statusCd />
                  <statusNm />
                  <ticketAmt />
                  <discountNm />
                  <printYN />
                  <payAmt />
                  <typeCd />
                  <typeName />
                  <reserveUserId />
                  <reserveDate />
               </reserveInfo>
            </UserReservationDto>
            """;

    @PostMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> post() {
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/xml-object", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<UserReservationDto> postObject() {
        UserReservationDto dto = new UserReservationDto();
        ReserveInfoDto reserveInfo = new ReserveInfoDto();
        reserveInfo.setBookingNo("1234");
        reserveInfo.setDayName("Monday");
        reserveInfo.setHallKr("aaaa");
        dto.setReserveInfo(List.of(reserveInfo));

        return ResponseEntity.ok(dto);
    }

    @Getter
    @Setter
    @XmlRootElement(name = "ArrayOfReserveInfo", namespace = "http://tempuri.org/")
    @XmlAccessorType(XmlAccessType.FIELD)
    @ToString
    static class UserReservationDto {

        private String random;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "ReserveInfo")
        private List<ReserveInfoDto> reserveInfo;
    }

    @Getter
    @Setter
    @ToString
    public static class ReserveInfoDto {
        private String reserveId;
        private String bookingNo;
        private String performanceId;
        private String performance;
        private String placeKr;
        private String hallKr;
        private String playYMD;
        private String startHM;
        private String dayName;
        private String playNum;
        private String scheCd;
        private String reserveCnt;
        private String reserveUser;
        private String pinCode;
        private String statusCd;
        private String statusNm;
        private String ticketAmt;
        private String discountNm;
        private String printYN;
        private String payAmt;
        private String typeCd;
        private String typeName;
        private String reserveUserId;
        private String reserveDate;
    }

    @Component
    static class Run implements CommandLineRunner {

        private final RestTemplate restTemplate = new RestTemplate();

        @Override
        public void run(String... args) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(new LinkedMultiValueMap<>(), headers);
            UserReservationDto result = restTemplate.postForObject("http://localhost:8080/xml", entity, UserReservationDto.class);

            System.out.println(result);
        }
    }


}
