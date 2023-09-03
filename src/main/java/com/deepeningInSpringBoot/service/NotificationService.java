package com.deepeningInSpringBoot.service;

import com.deepeningInSpringBoot.domain.user.User;
import com.deepeningInSpringBoot.dto.NotificarionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificarionDTO notificationRequest = new NotificarionDTO(email, message);

       ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);

       if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
           System.out.println("Erro ao enviar nitificações!");
           throw new Exception("Servição de notificação não está no ar!");
       }
    }
}
