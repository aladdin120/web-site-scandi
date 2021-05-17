package com.example.coursework.email;

import com.example.coursework.entities.Order;
import com.example.coursework.entities.User;
import com.example.coursework.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Async
    public void sendAddMessage(Order order) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("testjavasendler@gmail.com");
        message.setTo(userRepository.findUserById(order.getUserid()).getLogin());
        message.setSubject("Заказ принят");
        String text = "Здравствуйте, "+ userRepository.findUserById(order.getUserid()).getName() +" "+userRepository.findUserById(order.getUserid()).getSurname()
                +".\nВаш заказ: "+order.getDescription()+"\nГород: "+order.getCity()+"\nПринят в работу!";
        message.setText(text);

        javaMailSender.send(message);
    }

    @Async
    public void sendUpdateMessage(User user) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("testjavasendler@gmail.com");
        message.setTo(user.getLogin());
        message.setSubject("Изменение данных учетной записи");
        String text = "Здравствуйте!\nВаши данные изменены на:\nИмя: "+ user.getName() + "\nФамилия: "+user.getSurname()
                +"\nПочта: "+user.getLogin();
        message.setText(text);

        javaMailSender.send(message);
    }

    @Async
    public void sendNewMessage(User user) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("testjavasendler@gmail.com");
        message.setTo(user.getLogin());
        message.setSubject("Добро пожаловать в мебельный магазин Ros' Exec");
        String text = "Здравствуйте, "+user.getName()+" "+user.getSurname()+".\nРады приветствовать Вас у нас в магазине!";
        message.setText(text);

        javaMailSender.send(message);
    }
}

