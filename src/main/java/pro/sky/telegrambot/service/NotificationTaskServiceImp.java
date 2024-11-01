package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationTaskServiceImp implements NotificationTaskService {

    @Autowired
    private NotificationTaskRepository notificationTaskRepository;

    private final String REGEX = "(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)";
    private final String PATTERN = "dd.MM.yyyy HH:mm";

    @Override
    public String save(String message, long chatId) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(message);
        if (matcher.matches()) {
            String date = matcher.group(1);
            String text = matcher.group(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            NotificationTask notificationTask = new NotificationTask(chatId, text, dateTime);
            notificationTaskRepository.save(notificationTask);
            return "Напоминание сохранено!";
        }
        return "Неверный формат введенных данных!";
    }

    @Override
    public List<NotificationTask> getListOfCurrentNotificationTasks() {
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        return notificationTaskRepository.findTasksByDateTimeNotifications(dateTime);
    }
}
