package cn.wildfirechat.message.notification;

import cn.wildfirechat.message.Message;
import cn.wildfirechat.message.MessageContent;

/**
 * Created by heavyrainlee on 19/12/2017.
 */


public abstract class NotificationMessageContent extends MessageContent {
    /**
     * 是�?�是自己�?��?的
     * <p>
     * 用户�?�以�?用设置这个值，client会自动置上
     */
    public boolean fromSelf;

    public abstract String formatNotification(Message message);

    @Override
    public String digest(Message message) {
        return formatNotification(message);
    }
}
