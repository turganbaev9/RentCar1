package kg.mega.RentCar.controller;

import kg.mega.RentCar.service.FeignClient;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mail")
@RequiredArgsConstructor
public class MailSenderController {
private final FeignClient feignClient;
@PostMapping("/sendMail")
    public void sendMail(@RequestParam String to , @RequestParam String subject,@RequestParam String text){
    feignClient.sendMail(to,subject,text);
}
}

