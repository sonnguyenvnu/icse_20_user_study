package com.example.chat.manager;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.chat.R;
import com.example.chat.base.ConstantUtil;
import com.example.chat.bean.ChatMessage;
import com.example.chat.bean.GroupChatMessage;
import com.example.chat.bean.MessageContent;
import com.example.chat.mvp.main.invitation.InvitationActivity;
import com.example.commonlibrary.bean.chat.User;
import com.example.chat.mvp.main.HomeActivity;
import com.example.chat.util.FaceTextUtil;
import com.example.chat.util.LogUtil;
import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.bean.chat.GroupTableEntity;
import com.example.commonlibrary.utils.CommonLogger;

import java.util.List;

import androidx.core.app.NotificationCompat;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * 项目�??称:    TestChat
 * 创建人:        陈锦军
 * 创建时间:    2016/10/12      20:23
 * QQ:             1981367757
 */
public class ChatNotificationManager {
    private static final Object LOCK = new Object();
    private NotificationManager sNotificationManager;
    private static ChatNotificationManager instance;

    private ChatNotificationManager(Context context) {
        sNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    /**
     * �?�例模�?
     * �?��?�?定
     *
     * @param context context
     * @return �?�例
     */
    public static ChatNotificationManager getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new ChatNotificationManager(context);
                }
            }
        }
        return instance;
    }


    public void sendChatMessageNotification(ChatMessage chatMessage, Context context) {
        LogUtil.e("接�?��?功");
        //                                                                        这里进行监�?�回调到主页�?�
        int messageType = chatMessage.getMessageType();
        UserManager.getInstance().findUserById(chatMessage.getBelongId(), new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (list != null && list.size() > 0) {
                    if (messageType == ChatMessage.MESSAGE_TYPE_NORMAL) {
                        if (chatMessage.getContentType().equals(ConstantUtil.TAG_MSG_TYPE_IMAGE)) {
                            showNotification(ConstantUtil.NOTIFICATION_TAG_MESSAGE, context, list.get(0).getName(), R.mipmap.ic_launcher, "[图片]", HomeActivity.class);
                        } else if (chatMessage.getContentType().equals(ConstantUtil.TAG_MSG_TYPE_LOCATION)) {
                            showNotification(ConstantUtil.NOTIFICATION_TAG_MESSAGE, context, list.get(0).getName(), R.mipmap.ic_launcher, "[�?置]", HomeActivity.class);
                        } else if (chatMessage.getContentType().equals(ConstantUtil.TAG_MSG_TYPE_VOICE)) {
                            showNotification(ConstantUtil.NOTIFICATION_TAG_MESSAGE, context, list.get(0).getName(), R.mipmap.ic_launcher, "[语音]", HomeActivity.class);
                        } else if (chatMessage.getContentType().equals(ConstantUtil.TAG_MSG_TYPE_VIDEO)) {
                            showNotification(ConstantUtil.NOTIFICATION_TAG_MESSAGE, context, list.get(0).getName(), R.mipmap.ic_launcher, "[视频]", HomeActivity.class);
                        } else {
                            String content = BaseApplication.getAppComponent().getGson().fromJson(chatMessage.getContent(), MessageContent.class).getContent();
                            showNotification(ConstantUtil.NOTIFICATION_TAG_MESSAGE, context, list.get(0).getName(), R.mipmap.ic_launcher, FaceTextUtil.toSpannableString(context, content), HomeActivity.class);
                        }
                    } else if (messageType == ChatMessage.MESSAGE_TYPE_AGREE) {
                        showNotification(ConstantUtil.NOTIFICATION_TAG_AGREE, context, list.get(0).getName(), R.mipmap.ic_launcher, list.get(0).getName() + "已�?��?添加你为好�?�", HomeActivity.class);
                    } else if (messageType == ChatMessage.MESSAGE_TYPE_ADD) {
                        showNotification(ConstantUtil.NOTIFICATION_TAG_ADD, context, list.get(0).getName(), R.mipmap.ic_launcher, list.get(0).getName() + "请求添加你为好�?�", InvitationActivity.class);
                    }
                }
            }
        });
    }


    public void sendGroupMessageNotification(final GroupChatMessage message, final Context context) {
        GroupTableEntity groupTableEntity = UserDBManager.getInstance()
                .getGroupTableEntity(message.getGroupId());
        realSendGroupMessageNotification(groupTableEntity.getGroupName(), message, context);
    }


    private void realSendGroupMessageNotification(String name, GroupChatMessage groupChatMessage, Context context) {
        UserManager.getInstance().findUserById(groupChatMessage.getBelongId(), new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (list != null && list.size() > 0) {
                    if (groupChatMessage.getContentType().equals(ConstantUtil.TAG_MSG_TYPE_IMAGE)) {
                        showNotification(ConstantUtil.NOTIFICATION_TAG_MESSAGE, context, name, R.mipmap.ic_launcher, list.get(0).getName() + "：[图片]", HomeActivity.class);
                    } else if (groupChatMessage.getContentType().equals(ConstantUtil.TAG_MSG_TYPE_LOCATION)) {
                        showNotification(ConstantUtil.NOTIFICATION_TAG_MESSAGE, context, name, R.mipmap.ic_launcher, list.get(0).getName() + "：[�?置]", HomeActivity.class);
                    } else if (groupChatMessage.getContentType().equals(ConstantUtil.TAG_MSG_TYPE_VOICE)) {
                        showNotification(ConstantUtil.NOTIFICATION_TAG_MESSAGE, context, name, R.mipmap.ic_launcher, list.get(0).getName() + "：[语音]", HomeActivity.class);
                    } else {
                        showNotification(ConstantUtil.NOTIFICATION_TAG_MESSAGE, context, name, R.mipmap.ic_launcher, list.get(0).getName() + "：" + FaceTextUtil.toSpannableString(context, groupChatMessage.getContent()), HomeActivity.class);
                    }
                }
            }
        });
    }


    /**
     * 在通知�?展示通知
     *
     * @param notificationTagAdd 通知消�?�的类型标签
     * @param context            context
     * @param userName           用户�??
     * @param icon               通知�?图标
     * @param content            通知�?内容
     */
    public void showNotification(String notificationTagAdd, Context context, String userName, int icon, CharSequence content, Class<? extends Activity> targetClass) {
        boolean isAllowPushNotify = BaseApplication.getAppComponent()
                .getSharedPreferences().getBoolean(ConstantUtil.PUSH_NOTIFY, true);
        boolean isAllowVoice = BaseApplication.getAppComponent()
                .getSharedPreferences().getBoolean(ConstantUtil.VOICE_STATUS, true);
        boolean isAllowVibrate = BaseApplication.getAppComponent().getSharedPreferences().getBoolean(ConstantUtil.VIBRATE_STATUS, true);
        if (isAllowPushNotify) {
            ChatNotificationManager.getInstance(context).notify(notificationTagAdd, null, isAllowVibrate, isAllowVoice, context, userName, icon, content, targetClass);
            CommonLogger.e("�?��?通知到通知�?啦啦啦");
        }
    }


    /**
     * �?��?通知到通知�?
     *
     * @param isAllowVibrate 是�?��?许振动
     * @param isAllowVoice   是�?��?许声音
     * @param context        context
     * @param title          标题
     * @param icon           图标
     * @param content        内容
     * @param targetClass    目标Activity
     */
    public void notify(String tag, String groupId, boolean isAllowVibrate, boolean isAllowVoice, Context context, String title, int icon, CharSequence content, Class<? extends Activity> targetClass) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(icon);
        builder.setContentText(content);
        builder.setContentTitle(title);
        builder.setTicker(title);
        builder.setAutoCancel(true);
        if (isAllowVibrate) {
            builder.setDefaults(Notification.DEFAULT_VIBRATE);
        }
        if (isAllowVoice) {
            builder.setDefaults(Notification.DEFAULT_SOUND);
        }
        Intent intent;
        if (targetClass != null) {
            intent = new Intent(context, targetClass);
        } else {
            intent = new Intent("custom.activity.action.main");
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(ConstantUtil.NOTIFICATION_TAG, tag);
        if (groupId != null) {
            intent.putExtra(ConstantUtil.GROUP_ID, groupId);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        sNotificationManager.notify(ConstantUtil.NOTIFY_ID, builder.build());
    }
}
