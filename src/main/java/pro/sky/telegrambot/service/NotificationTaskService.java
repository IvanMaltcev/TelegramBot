package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.NotificationTask;

import java.util.List;

public interface NotificationTaskService {

    String save(String message, long chatId);

    List<NotificationTask> getListOfCurrentNotificationTasks();

}
