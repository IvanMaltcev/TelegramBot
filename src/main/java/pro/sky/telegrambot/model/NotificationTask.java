package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notificationtask")
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "notification_text")
    private String notificationText;
    @Column(name = "date_time_notifications")
    private LocalDateTime dateTimeNotifications;

    public NotificationTask() {
    }

    public NotificationTask(Long chatId,
                            String notificationText,
                            LocalDateTime dateTimeNotifications) {
        this.chatId = chatId;
        this.notificationText = notificationText;
        this.dateTimeNotifications = dateTimeNotifications;
    }

    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public LocalDateTime getDateTimeNotifications() {
        return dateTimeNotifications;
    }

    public void setDateTimeNotifications(LocalDateTime dateAndTimeNotifications) {
        this.dateTimeNotifications = dateAndTimeNotifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(id, that.id)
                && Objects.equals(chatId, that.chatId)
                && Objects.equals(notificationText, that.notificationText)
                && Objects.equals(dateTimeNotifications, that.dateTimeNotifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, notificationText, dateTimeNotifications);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id = " + id +
                ", chatId = " + chatId +
                ", notificationText = '" + notificationText + '\'' +
                ", dateAndTimeNotifications = " + dateTimeNotifications +
                '}';
    }
}
