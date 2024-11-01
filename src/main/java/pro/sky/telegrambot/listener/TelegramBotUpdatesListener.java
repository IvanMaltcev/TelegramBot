package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.service.NotificationTaskService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private NotificationTaskService notificationTaskService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String message = update.message().text();
            long chatId = update.message().chat().id();
            if (message.equals("/start")) {
                String textToSend = "Добро пожаловать в бот-напоминалку!";
                SendMessage answer = new SendMessage(chatId, textToSend);
                telegramBot.execute(answer);
                return;
            }
            String textToSend = notificationTaskService.save(message, chatId);
            SendMessage answer = new SendMessage(chatId, textToSend);
            telegramBot.execute(answer);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        List<NotificationTask> notificationTasks =
                notificationTaskService.getListOfCurrentNotificationTasks();
        if (!notificationTasks.isEmpty()) {
            notificationTasks.forEach(notificationTask -> {
                long chatId = notificationTask.getChatId();
                String notificationText = notificationTask.getNotificationText();
                SendMessage answer = new SendMessage(chatId, notificationText);
                telegramBot.execute(answer);
            });
        }
        System.out.println(notificationTasks);
    }

}
