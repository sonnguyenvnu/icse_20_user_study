package com.example.chat.manager;

import android.content.Context;

import com.example.chat.base.ConstantUtil;
import com.example.chat.bean.BaseMessage;
import com.example.chat.bean.ChatMessage;
import com.example.chat.bean.CommentNotifyBean;
import com.example.chat.bean.CustomInstallation;
import com.example.chat.bean.GroupChatMessage;
import com.example.chat.bean.GroupTableMessage;
import com.example.chat.bean.MessageContent;
import com.example.chat.bean.PostNotifyBean;
import com.example.chat.bean.SkinBean;
import com.example.chat.bean.StepBean;
import com.example.chat.bean.SystemNotifyBean;
import com.example.chat.bean.post.CommentDetailBean;
import com.example.chat.bean.post.PostDataBean;
import com.example.chat.bean.post.PublicCommentBean;
import com.example.chat.bean.post.PublicPostBean;
import com.example.chat.bean.post.ReplyCommentListBean;
import com.example.chat.bean.post.ReplyDetailContent;
import com.example.chat.events.MessageInfoEvent;
import com.example.chat.listener.AddFriendCallBackListener;
import com.example.chat.listener.OnCreateChatMessageListener;
import com.example.chat.listener.OnCreateGroupTableListener;
import com.example.chat.listener.OnCreatePublicPostListener;
import com.example.chat.listener.OnReceiveListener;
import com.example.chat.listener.OnSendTagMessageListener;
import com.example.chat.util.JsonUtil;
import com.example.chat.util.LogUtil;
import com.example.chat.util.TimeUtil;
import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.bean.chat.ChatMessageEntity;
import com.example.commonlibrary.bean.chat.DaoSession;
import com.example.commonlibrary.bean.chat.GroupChatEntity;
import com.example.commonlibrary.bean.chat.GroupTableEntity;
import com.example.commonlibrary.bean.chat.PostCommentEntity;
import com.example.commonlibrary.bean.chat.PostNotifyInfo;
import com.example.commonlibrary.bean.chat.PublicPostEntity;
import com.example.commonlibrary.bean.chat.PublicPostEntityDao;
import com.example.commonlibrary.bean.chat.RecentMessageEntity;
import com.example.commonlibrary.bean.chat.SkinEntity;
import com.example.commonlibrary.bean.chat.SkinEntityDao;
import com.example.commonlibrary.bean.chat.StepData;
import com.example.commonlibrary.bean.chat.User;
import com.example.commonlibrary.bean.chat.UserEntityDao;
import com.example.commonlibrary.rxbus.RxBusManager;
import com.example.commonlibrary.utils.CommonLogger;
import com.example.commonlibrary.utils.SystemUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.PushListener;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;
import rx.Subscription;

/**
 * 项目�??称:    TestChat
 * 创建人:        陈锦军
 * 创建时间:    2016/10/9      12:16
 * QQ:             1981367757
 */
public class MsgManager {

    private static MsgManager instance;
    private BmobPushManager<CustomInstallation> mPushManager;
    private Gson gson;
    /**
     * 用于�?�例模�?的�?��?�?定
     */
    private static final Object LOCK = new Object();

    private MsgManager() {
        mPushManager = new BmobPushManager<>();
        gson = BaseApplication.getAppComponent().getGson();
    }

    public static MsgManager getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new MsgManager();
                }
            }
        }
        return instance;
    }

    /**
     * �?��?标签消�?�
     * �?�?存在本地数�?�库    ,�?��?完�?�上传消�?�到�?务器上�?�
     *
     * @param targetId    对方的ID
     * @param messageType 消�?�的类型
     * @param listener    回调
     */
    public void sendTagMessage(final String targetId, int messageType, final OnSendTagMessageListener listener) {
        final ChatMessage msg = createTagMessage(targetId, messageType);
        //                                  在这里�?��?完�?��?请求�?�，把消�?�转为对方�?��?的消�?�
        if (messageType == ChatMessage.MESSAGE_TYPE_AGREE) {
            UserDBManager.getInstance().addOrUpdateRecentMessage(msg);
            LogUtil.e("�?存�?��?消�?�到�?�天消�?�表中");
            //                                    这里将�?��?的欢迎消�?�转为对方�?��?
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setToId(msg.getBelongId());
            chatMessage.setMessageType(msg.getMessageType());
            chatMessage.setConversationId(targetId + "&" + UserManager
                    .getInstance().getCurrentUserObjectId());
            chatMessage.setBelongId(msg.getToId());
            chatMessage.setCreateTime(msg.getCreateTime());
            chatMessage.setSendStatus(msg.getSendStatus());
            chatMessage.setReadStatus(ConstantUtil.RECEIVE_UNREAD);
            chatMessage.setContentType(ConstantUtil.TAG_MSG_TYPE_TEXT);
            chatMessage.setContent(msg.getContent());
            UserDBManager.getInstance()
                    .addOrUpdateChatMessage(chatMessage);
        }
        saveMessageToService(msg, new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    listener.onSuccess(msg);
                    findInstallation(targetId, new FindListener<CustomInstallation>() {
                        @Override
                        public void done(List<CustomInstallation> list, BmobException e) {
                            if (e == null && list != null && list.size() > 0) {
                                sendJsonMessage(list.get(0).getInstallationId(), createJsonMessage(msg), null);
                            }
                        }
                    });
                } else {
                    listener.onFailed(e);
                }
            }
        });


    }

    private void findInstallation(String uid, FindListener<CustomInstallation> listener) {
        BmobQuery<CustomInstallation> query = new BmobQuery<>();
        query.addWhereEqualTo("uid", uid);
        query.findObjects(listener);
    }

    /**
     * 上传消�?�到�?务器中
     *
     * @param msg 消�?�
     */
    private void saveMessageToService(final ChatMessage msg, SaveListener<String> listener) {
        if (msg.getMessageType() == ChatMessage.MESSAGE_TYPE_READED) {
            //            添加这一步验�?主�?是为了防止Bmob有时候上传多个回执消�?�的Bug,
            findReadTag(msg.getConversationId(), msg.getCreateTime(), new FindListener<ChatMessage>() {
                @Override
                public void done(List<ChatMessage> list, BmobException e) {
                    if (list == null || list.size() == 0) {
                        msg.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    LogUtil.e("�?存消�?�到�?务器上�?功");
                                } else {
                                    LogUtil.e("�?存消�?�到�?务器上失败:" + e.toString());
                                }
                                if (listener != null) {
                                    listener.done(s, e);
                                }
                            }
                        });
                    } else {
                        if (listener != null) {
                            listener.done(null, e);
                        }
                    }
                }
            });
        } else {
            msg.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        LogUtil.e("�?存消�?�到�?务器上�?功");
                    } else {
                        LogUtil.e("�?存消�?�到�?务器上失败:" + e.toString());
                    }

                    if (listener != null) {
                        listener.done(s, e);
                    }
                }

            });
        }
    }


    public void getPersonChatInfo(Context context) {
        BmobQuery<ChatMessage> query = new BmobQuery<>();
        if (UserManager.getInstance().getCurrentUser() != null) {
            query.addWhereEqualTo(ConstantUtil.TAG_TO_ID, UserManager.getInstance().getCurrentUserObjectId());
        } else {
            return;
        }
        query.addWhereEqualTo(ConstantUtil.TAG_MESSAGE_SEND_STATUS, ConstantUtil.SEND_STATUS_SUCCESS);
        query.addWhereEqualTo(ConstantUtil.TAG_MESSAGE_READ_STATUS, ConstantUtil.READ_STATUS_UNREAD);
        //                按�?��?进行排�?
        query.setLimit(50);
        query.order("createdAt");
        query.findObjects(new FindListener<ChatMessage>() {
            @Override
            public void done(List<ChatMessage> list, BmobException e) {
                if (e == null) {
                    LogUtil.e("1拉�?��?��?�消�?��?功");
                    if (list != null && list.size() > 0) {
                        for (ChatMessage item :
                                list) {
                            MsgManager.getInstance().dealReceiveChatMessage(item, new OnReceiveListener() {
                                @Override
                                public void onSuccess(BaseMessage baseMessage) {
                                    if (baseMessage instanceof ChatMessage) {
                                        ChatMessage chatMessage = (ChatMessage) baseMessage;
                                        CommonLogger.e("接�?��?功");
                                        LogUtil.e(chatMessage);
                                        List<ChatMessage> list = new ArrayList<>(1);
                                        list.add(chatMessage);
                                        MessageInfoEvent messageInfoEvent = new MessageInfoEvent(MessageInfoEvent.TYPE_PERSON);
                                        messageInfoEvent.setChatMessageList(list);
                                        //                        �?�天消�?�
                                        RxBusManager.getInstance().post(messageInfoEvent);
                                        ChatNotificationManager.getInstance(context).sendChatMessageNotification(chatMessage, context);
                                    }
                                }

                                @Override
                                public void onFailed(BmobException e) {
                                    LogUtil.e("接�?�消�?�失败!>>>>" + e.getMessage() + e.getErrorCode());
                                }
                            });
                        }
                    }
                } else {
                    LogUtil.e("拉�?��?��?�消�?�失败");
                    LogUtil.e("在�?务器上查询�?�天消�?�失败：" + e.toString());
                }
            }
        });
    }


    /**
     * 创建json
     *
     * @param message 消�?�
     * @return 放回json
     */
    private JSONObject createJsonMessage(ChatMessage message) {
        try {
            JSONObject result = new JSONObject();
            result.put(ConstantUtil.TAG_MESSAGE_READ_STATUS, message.getReadStatus());
            result.put(ConstantUtil.TAG_MESSAGE_SEND_STATUS, message.getSendStatus());
            result.put(ConstantUtil.TAG_CONTENT, message.getContent());
            result.put(ConstantUtil.TAG_CONVERSATION, message.getConversationId());
            result.put(ConstantUtil.TAG_CONTENT_TYPE, message.getContentType());
            result.put(ConstantUtil.TAG_MESSAGE_TYPE, message.getMessageType());
            result.put(ConstantUtil.TAG_CREATE_TIME, message.getCreateTime());
            result.put(ConstantUtil.TAG_BELONG_ID, message.getBelongId());
            result.put(ConstantUtil.TAG_TO_ID, message.getToId());
            CommonLogger.e("组装�?�的json:" + result.toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建标签实体消�?�类  目�?有的标签类型消�?�：1�?邀请消�?�2�?�?��?消�?�3�?回执消�?�
     *
     * @param targetId 对方ID
     * @return 标签实体类
     */
    public ChatMessage createTagMessage(String targetId, int messageType) {
        return createTagMessage(targetId, UserManager.getInstance().getCurrentUserObjectId() + "&" + targetId
                , System.currentTimeMillis(), messageType);
    }

    public ChatMessage createTagMessage(String targetId, String conversationId, Long time, int messageType) {
        User user = UserManager.getInstance().getCurrentUser();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setToId(targetId);
        chatMessage.setMessageType(messageType);
        chatMessage.setConversationId(conversationId);
        chatMessage.setBelongId(user.getObjectId());
        chatMessage.setCreateTime(time);
        chatMessage.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
        chatMessage.setReadStatus(ConstantUtil.READ_STATUS_UNREAD);
        chatMessage.setContentType(ConstantUtil.TAG_MSG_TYPE_TEXT);
        if (messageType == ChatMessage.MESSAGE_TYPE_AGREE) {
            MessageContent messageContent = new MessageContent();
            messageContent.setContent("你们已�?�?为好�?��?�以�?�天啦啦啦");
            chatMessage.setContent(gson.toJson(messageContent));
        }
        return chatMessage;

    }


    /**
     * 根�?�用户id到�?��?��?务器去查找用户
     *
     * @param targetId     对方用户ID
     * @param findListener 回调
     */
    private void getUserById(String targetId, FindListener<User> findListener) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId", targetId);
        query.findObjects(findListener);
    }

    /**
     * 从json数�?�中解�?和组�?接�?�的�?�天消�?�(�?是标签消�?�)   并�?存和上传到�?务器上和�?�回执消�?�
     *
     * @param json     json数�?�
     * @param listener 回调
     */
    public void createReceiveMsg(String json, OnReceiveListener listener) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            final ChatMessage message = new ChatMessage();
            message.setContentType(JsonUtil.getInt(jsonObject, ConstantUtil.TAG_CONTENT_TYPE));
            message.setToId(JsonUtil.getString(jsonObject, ConstantUtil.TAG_TO_ID));
            message.setBelongId(JsonUtil.getString(jsonObject, ConstantUtil.TAG_BELONG_ID));
            message.setCreateTime(JsonUtil.getLong(jsonObject, ConstantUtil.TAG_CREATE_TIME));
            message.setMessageType(JsonUtil.getInt(jsonObject, ConstantUtil.TAG_MESSAGE_TYPE));
            message.setConversationId(JsonUtil.getString(jsonObject, ConstantUtil.TAG_CONVERSATION));
            message.setReadStatus(ConstantUtil.RECEIVE_UNREAD);
            message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
            message.setContent(JsonUtil.getString(jsonObject, ConstantUtil.TAG_CONTENT));
            dealReceiveChatMessage(message, listener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void dealReceiveGroupChatMessage(GroupChatMessage groupChatMessage) {
        UserDBManager.getInstance().addOrUpdateGroupChatMessage(groupChatMessage);
        UserDBManager.getInstance().addOrUpdateRecentMessage(groupChatMessage);
    }


    public void dealReceiveGroupChatMessage(List<GroupChatMessage> groupChatMessageList) {
        UserDBManager.getInstance().addOrUpdateGroupChatMessage(groupChatMessageList);
        if (groupChatMessageList.size() > 0) {
            UserDBManager.getInstance().addOrUpdateRecentMessage(groupChatMessageList.get(0));
        }
    }

    public void dealReceiveChatMessage(ChatMessage message, final OnReceiveListener listener) {
        message.setReadStatus(ConstantUtil.RECEIVE_UNREAD);
        message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
        switch (message.getMessageType()) {
            case ChatMessage.MESSAGE_TYPE_AGREE:
                LogUtil.e("接收到�?��?消�?�");
                if (!UserDBManager.getInstance().hasMessage(message.getConversationId(), message.getCreateTime())) {
                    UserManager.getInstance().addNewFriend(message.getBelongId(), message.getToId(), new AddFriendCallBackListener() {
                        @Override
                        public void onSuccess(User user) {
                            updateMsgReaded(false, message.getConversationId(), message.getCreateTime());
                            UserDBManager.getInstance().addChatMessage(message);
                            UserDBManager.getInstance().addOrUpdateRecentMessage(message);
                            listener.onSuccess(message);
                        }

                        @Override
                        public void onFailed(BmobException e) {
                            listener.onFailed(e);
                        }
                    });
                } else {
                    updateMsgReaded(false, message.getConversationId(), message.getCreateTime());
                }
                break;
            case ChatMessage.MESSAGE_TYPE_ADD:
                if (!UserDBManager.getInstance().hasMessage(message.getConversationId(), message.getCreateTime())) {
                    UserDBManager.getInstance().addChatMessage(message);
                    //                    这里获�?�邀请列表的用户数�?�
                    UserManager.getInstance().findUserById(message.getBelongId(), null);
                    updateMsgReaded(false, message.getConversationId(), message.getCreateTime());
                    listener.onSuccess(message);
                } else {
                    updateMsgReaded(false, message.getConversationId(), message.getCreateTime());
                }
                break;
            case ChatMessage.MESSAGE_TYPE_READED:
                if (!UserDBManager.getInstance().hasReadMessage(message.getConversationId(), message.getCreateTime())) {
                    UserDBManager.getInstance().addChatMessage(message);
                    updateMsgReaded(true, message.getConversationId(), message.getCreateTime());
                    UserDBManager.getInstance().updateMessageReadStatus(message.getConversationId(), message.getCreateTime(), ConstantUtil.READ_STATUS_READED);
                    listener.onSuccess(message);
                }
                break;
            default:
                //                                �?�天消�?�
                //                                接收到的消�?�有�?情况，1�?推�?接收到的消�?�，已�?在检测得到了，所以推�?的就�?�?了
                if (!UserDBManager.getInstance().hasMessage(message.getConversationId(), message.getCreateTime())) {
                    UserDBManager.getInstance().addChatMessage(message);
                    UserDBManager.getInstance().addOrUpdateRecentMessage(message);
                    updateMsgReaded(false, message.getConversationId(), message.getCreateTime());
                    sendAskReadMsg(message.getConversationId(), message.getCreateTime());
                    listener.onSuccess(message);
                } else {
                    updateMsgReaded(false, message.getConversationId(), message.getCreateTime());
                }
                break;
        }
    }

    public GroupChatMessage createReceiveGroupChatMsg(JSONObject jsonObject) {
        GroupChatMessage message = new GroupChatMessage();
        message.setGroupId(JsonUtil.getString(jsonObject, ConstantUtil.GROUP_ID));
        message.setContent(JsonUtil.getString(jsonObject, ConstantUtil.TAG_CONTENT));
        message.setReadStatus(ConstantUtil.RECEIVE_UNREAD);
        message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
        message.setCreateTime(JsonUtil.getLong(jsonObject, ConstantUtil.TAG_CREATE_TIME));
        message.setBelongId(JsonUtil.getString(jsonObject, ConstantUtil.TAG_BELONG_ID));
        message.setContentType(JsonUtil.getInt(jsonObject, ConstantUtil.TAG_CONTENT_TYPE));
        message.setObjectId(JsonUtil.getString(jsonObject, ConstantUtil.ID));
        return message;
    }


    /**
     * 根�?�会�?ID和消�?�的创建时间�?��?��?一个回执已读消�?�
     *
     * @param conversationId 会�?id
     * @param createTime     消�?�创建时间
     */
    private void sendAskReadMsg(final String conversationId, final Long createTime) {
        final ChatMessage chatMessage = createTagMessage(conversationId.split("&")[0], conversationId, createTime, ChatMessage.MESSAGE_TYPE_READED);
        saveMessageToService(chatMessage, new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    findInstallation(conversationId.split("&")[0], new FindListener<CustomInstallation>() {
                        @Override
                        public void done(List<CustomInstallation> list, BmobException e) {
                            if (e == null && list != null && list.size() > 0) {
                                sendJsonMessage(list.get(0).getInstallationId(), createJsonMessage(chatMessage), null);
                            }
                        }
                    });
                } else {
                    CommonLogger.e("�?存到�?务器已读类型消�?�失败" + e.toString());
                }
            }
        });
    }


    /**
     * 在�?务器上�?�更新�?�天消�?�的已读状�?(首先根�?�用户ID在�?务器上查询获�?�该消�?�,然�?��?更新该消�?�)
     *
     * @param isReadedMessage 查询的标志
     * @param id              会�?ID或者是belongID
     * @param createTime      创建时间
     */
    public void updateMsgReaded(boolean isReadedMessage, String id, Long createTime) {
        queryMsg(isReadedMessage, id, createTime, new FindListener<ChatMessage>() {
                    @Override
                    public void done(List<ChatMessage> list, BmobException e) {
                        if (e == null) {
                            if (list != null && list.size() > 0) {
                                final ChatMessage chatMessage = list.get(0);
                                chatMessage.setReadStatus(ConstantUtil.READ_STATUS_READED);
                                chatMessage.update(new UpdateListener() {
                                                       @Override
                                                       public void done(BmobException e) {
                                                           if (e == null) {
                                                               LogUtil.e("更新�?务器上的消�?�已读状�?�?功11111");
                                                           } else {
                                                               LogUtil.e("更新�?务器上的消�?�已读消�?�失败" + e.toString());
                                                           }
                                                       }
                                                   }
                                );
                            }
                        } else {
                            LogUtil.e("查找�?�天消�?�失败" + e.toString());
                        }
                    }
                }
        );
    }

    /**
     * 根�?�是�?�是标签和ID值(conversation 或  belongId)和创建时间�?�查询消�?�
     *
     * @param isReadedMessage 是�?�是标签消�?�
     * @param id              会�?id或者是用户ID
     * @param createTime      创建时间
     * @param findListener    找到消�?�的回调
     */
    private void queryMsg(boolean isReadedMessage, String id, Long createTime, FindListener<ChatMessage> findListener) {
        BmobQuery<ChatMessage> query = new BmobQuery<>();
        query.addWhereEqualTo("conversationId", id);
        query.addWhereEqualTo("createTime", createTime);
        if (isReadedMessage) {
            query.addWhereEqualTo("messageType", ChatMessage.MESSAGE_TYPE_READED);
        }
        query.findObjects(findListener);
    }


    public void queryGroupChatMessage(String groupId, FindListener<GroupChatMessage> listener) {
        if (groupId == null) {
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(groupId);
        queryGroupChatMessage(list, listener);
    }


    public void queryGroupChatMessage(List<String> groupIdList, final FindListener<GroupChatMessage> listener) {
        if (groupIdList != null && groupIdList.size() > 0) {
            LogUtil.e("群id列表如下");
            for (int i = 0; i < groupIdList.size(); i++) {
                String groupId = groupIdList.get(i);
                LogUtil.e(groupId);
                BmobQuery<GroupChatMessage> query = new BmobQuery<>("g" + groupId);
                final RecentMessageEntity recentMsg = UserDBManager.getInstance().getRecentMessage(groupId);
                long lastTime;
                if (recentMsg == null) {
                    lastTime = 0;
                } else {
                    lastTime = recentMsg.getCreatedTime();
                }
                query.addWhereGreaterThan("updatedAt", new BmobDate(new Date(lastTime)));
                query.order("-updatedAt");
                query.findObjectsByTable(new QueryListener<JSONArray>() {
                    @Override
                    public void done(JSONArray jsonArray, BmobException e) {
                        if (e == null) {
                            LogUtil.e("群消�?�解�?");
                            LogUtil.e("jsonArray：" + jsonArray.toString());
                            List<GroupChatMessage> list = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    GroupChatMessage groupChatMessage = MsgManager.getInstance().createReceiveGroupChatMsg(jsonObject);
                                    list.add(groupChatMessage);
                                } catch (JSONException item) {
                                    item.printStackTrace();
                                }
                            }
                            dealReceiveGroupChatMessage(list);
                            listener.done(list, null);
                        } else {
                            LogUtil.e("查询断网期间的群消�?�失败：" + e.toString());
                            listener.done(null, e);
                        }
                    }


                });
            }
        }
    }


    /**
     * 根�?�内容和目标ID创建消�?�实体
     *
     * @param content 内容
     * @param uid     用户ID
     * @return 消�?�实体
     */
    public ChatMessage createChatMessage(String content, String uid, int contentType) {
        User currentUser = UserManager.getInstance().getCurrentUser();
        ChatMessage message = new ChatMessage();
        message.setBelongId(currentUser.getObjectId());
        message.setToId(uid);
        message.setConversationId(currentUser.getObjectId() + "&" + uid);
        //                 默认设置消�?��?��?�?功
        message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
        message.setReadStatus(ConstantUtil.READ_STATUS_UNREAD);
        message.setContent(content);
        message.setCreateTime(System.currentTimeMillis());
        message.setContentType(contentType);
        message.setMessageType(ChatMessage.MESSAGE_TYPE_NORMAL);
        return message;
    }

    /**
     * 给其他设备�?��?下线通知(除了本设备)
     *
     * @param customInstallation 设备列表
     * @param listener           推�?监�?�
     */
    void sendOfflineNotificationMsg(CustomInstallation customInstallation, PushListener listener) {
        JSONObject offlineJsonObject = createOfflineJsonObject();
        sendJsonMessage(customInstallation.getInstallationId(), offlineJsonObject, listener);
    }

    /**
     * 创建下载通知的jsonObject
     *
     * @return 下载通知的jsonObject
     */
    private JSONObject createOfflineJsonObject() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(ConstantUtil.MESSAGE_TAG, ConstantUtil.TAG_OFFLINE);
            jsonObject.put(ConstantUtil.TAG_BELONG_ID, UserManager.getInstance().getCurrentUserObjectId());
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 推�?消�?�
     *
     * @param installationId 推�?的设备ID
     * @param jsonObject     推�?的jsonObject
     */
    private void sendJsonMessage(String installationId, JSONObject jsonObject, PushListener listener) {
        BmobQuery<CustomInstallation> query = new BmobQuery<>();
        query.addWhereEqualTo("installationId", installationId);
        mPushManager.setQuery(query);
        mPushManager.pushMessage(jsonObject, listener);
    }


    public void sendJsonMessage(List<String> installationIdList, String json, PushListener pushListener) {
        BmobQuery<CustomInstallation> query = new BmobQuery<>();
        query.addWhereContainedIn("installationId", installationIdList);
        mPushManager.setQuery(query);
        mPushManager.pushMessage(json, pushListener);
    }


    public void sendJsonMessage(String installId, String json, PushListener pushListener) {
        BmobQuery<CustomInstallation> query = new BmobQuery<>();
        query.addWhereEqualTo("installationId", installId);
        mPushManager.setQuery(query);
        mPushManager.pushMessage(json, pushListener);
    }


    /**
     * �?��?建群消�?�
     * <p>
     * 主�?�?程：
     * 1�?构建群结构消�?�，�?存到�?务器上
     * 2�?从�?务器上获�?�该消�?�，并把该消�?�的id设置为群id,�?�groupId
     * 3�?推�?群结构消�?�到�?个 �?员中
     * 4�?为�?个�?员在�?务器上�?存群结构消�?�，是为了防止推�?失败时，对方�?能获�?�到�?务器上的群结构消�?�，也就�?能定时拉�?�到群数�?�
     * 5�?群主�?��?群欢迎消�?�给�?个�?员
     *
     * @param groupName        群组姓�??
     * @param groupDescription 群组�??述
     * @param contacts         群组�?员(还没包括群主)
     */
    public void sendCreateGroupMessage(String groupName, final String groupDescription, final List<String> contacts, final OnCreateGroupTableListener listener) {
        //                这里�?把群主消�?�加进�?�
        contacts.add(0, UserManager.getInstance().getCurrentUser().getObjectId());
        GroupTableMessage message = createGroupTableMessage(groupName, groupDescription, contacts);
        message.setReadStatus(ConstantUtil.READ_STATUS_READED);
        message.save(new SaveListener<String>() {
                         @Override
                         public void done(String s, BmobException e) {
                             if (e == null) {
                                 message.setObjectId(s);
                                 message.setGroupId(message.getObjectId());
                                 message.update(new UpdateListener() {
                                     @Override
                                     public void done(BmobException e) {
                                         if (e == null) {
                                             UserDBManager.getInstance().addOrUpdateGroupTable(message);
                                             //            这里先上传�?推�?，因为对方�?�能接收到推�?信�?�的时候无法�?�时获�?�到�?务器上的群结构消�?�,也就查询信�?�失败
                                             //  todo  群结构的解决
                                             uploadChatTableMessage(message, new QueryListListener<BatchResult>() {
                                                 @Override
                                                 public void done(List<BatchResult> list, BmobException e) {

                                                     if (e == null) {
                                                         LogUtil.e("批�?�?存群结构消�?��?功");
                                                         MessageContent messageContent = new MessageContent();
                                                         messageContent.setContent("大家好");
                                                         sendGroupChatMessage(createGroupChatMessage(gson.toJson(messageContent)
                                                                 , message.getGroupId(), ConstantUtil.TAG_MSG_TYPE_TEXT), new OnCreateChatMessageListener() {
                                                             @Override
                                                             public void onSuccess(BaseMessage baseMessage) {
                                                                 UserDBManager.getInstance().addOrUpdateGroupChatMessage((GroupChatMessage) baseMessage);

                                                                 UserDBManager.getInstance().addOrUpdateRecentMessage(baseMessage);

                                                                 UserDBManager
                                                                         .getInstance().addOrUpdateGroupTable(message);

                                                                 listener.done(message, null);
                                                             }

                                                             @Override
                                                             public void onFailed(String errorMsg, BaseMessage baseMessage) {
                                                                 UserDBManager
                                                                         .getInstance().addOrUpdateGroupTable(message);
                                                                 listener.done(message, null);
                                                             }
                                                         });
                                                     } else {
                                                         listener.done(null, e);
                                                         LogUtil.e("批�?�?存群结构消�?�失败" + e.toString());
                                                     }
                                                     listener.done(null, e);
                                                 }
                                             });
                                         } else {
                                             LogUtil.e("更新群主的群结构消�?�失败" + e.toString());
                                             listener.done(null, e);
                                         }
                                     }

                                 });
                             } else {
                                 LogUtil.e("�?存群主所建的群结构消�?�到�?务器上失败" + e.toString());
                                 listener.done(null, e);
                             }

                         }
                     }
        );
    }


    private void uploadChatTableMessage(final GroupTableMessage groupTableMessage, QueryListListener<BatchResult> listener) {
        List<String> groupNumber = groupTableMessage.getGroupNumber();
        GroupTableMessage message;
        List<String> copy = new ArrayList<>(groupNumber);
        if (copy.contains(UserManager.getInstance().getCurrentUserObjectId())) {
            copy.remove(UserManager.getInstance().getCurrentUserObjectId());
        }
        List<BmobObject> groupTableMessageList = new ArrayList<>();
        for (int i = 0; i < copy.size(); i++) {
            message = new GroupTableMessage();
            message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
            message.setReadStatus(ConstantUtil.READ_STATUS_UNREAD);
            message.setGroupDescription(groupTableMessage.getGroupDescription());
            message.setGroupId(groupTableMessage.getGroupId());
            message.setCreatedTime(groupTableMessage.getCreatedTime());
            message.setToId(copy.get(i));
            message.setGroupNumber(groupNumber);
            message.setGroupAvatar(groupTableMessage.getGroupAvatar());
            message.setGroupName(groupTableMessage.getGroupName());
            message.setNotification(groupTableMessage.getNotification());
            message.setCreatorId(groupTableMessage.getCreatorId());
            message.setRemind(message.getRemind());
            groupTableMessageList.add(message);
        }
        new BmobBatch().insertBatch(groupTableMessageList).doBatch(listener);
    }

    private GroupTableMessage createGroupTableMessage(String groupName, String groupDescription, List<String> contacts) {
        User currentUser = UserManager.getInstance().getCurrentUser();
        GroupTableMessage message = new GroupTableMessage();
        message.setGroupName(groupName);
        message.setGroupDescription(groupDescription);
        message.setGroupAvatar(currentUser.getAvatar() == null ? "" : currentUser.getAvatar());
        message.setCreatorId(UserManager.getInstance().getCurrentUserObjectId());
        message.setGroupNumber(contacts);
        message.setCreatedTime(System.currentTimeMillis());
        message.setReadStatus(ConstantUtil.READ_STATUS_UNREAD);
        message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
        message.setToId(UserManager.getInstance().getCurrentUserObjectId());
        message.setNotification("");
        message.setRemind(Boolean.TRUE);
        return message;
    }


    public void queryGroupTableMessage(String uid, FindListener<GroupTableMessage> findListener) {
        BmobQuery<GroupTableMessage> query = new BmobQuery<>();
        query.addWhereEqualTo(ConstantUtil.TAG_TO_ID, uid);
        query.findObjects(findListener);
    }

    public GroupChatMessage createGroupChatMessage(String content, String groupId, int contentType) {
        GroupChatMessage message = new GroupChatMessage();
        message.setGroupId(groupId);
        message.setContent(content);
        message.setReadStatus(ConstantUtil.READ_STATUS_UNREAD);
        message.setContentType(contentType);
        message.setBelongId(UserManager.getInstance().getCurrentUserObjectId());
        message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
        message.setCreateTime(System.currentTimeMillis());
        return message;
    }


    public GroupTableMessage createReceiveGroupTableMsg(String json) {
        GroupTableMessage message = gson.fromJson(json, GroupTableMessage.class);
        LogUtil.e("实时监�?�到的群结构消�?�如下1");
        if (message != null) {
            LogUtil.e(message);
        }
        return message;
    }


    public User createUserFromJsonObject(JSONObject jsonObject) {
        User user;
        user = gson.fromJson(jsonObject.toString(), User.class);
        LogUtil.e(user);
        return user;
    }


    private void findReadTag(String conversationId, long createTime, FindListener<ChatMessage> findListener) {
        BmobQuery<ChatMessage> query = new BmobQuery<>();
        query.addWhereEqualTo("conversationId", conversationId);
        query.addWhereEqualTo("createTime", createTime);
        query.addWhereEqualTo("messageType", ChatMessage.MESSAGE_TYPE_READED);
        query.findObjects(findListener);
    }


    public void getAllDefaultAvatarFromServer(final FindListener<String> findListener) {
        BmobQuery bmobQuery = new BmobQuery("sys_data");
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        bmobQuery.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                if (e == null) {
                    List<String> avatarList = null;
                    if (jsonArray != null && jsonArray.length() > 0) {
                        LogUtil.e(jsonArray.toString());
                        avatarList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject avatarJson = jsonObject.getJSONObject("avatar");
                                String avatar = avatarJson.getString("url");
                                if (avatar != null) {
                                    avatarList.add(avatar);
                                }
                            } catch (JSONException item) {
                                item.printStackTrace();
                            }
                        }
                    }
                    if (avatarList == null) {
                        LogUtil.e("�?务器上�?�没有头�?数�?�");
                    }
                    findListener.done(avatarList, null);
                } else {
                    findListener.done(null, e);
                }
            }

        });
    }


    public void updateGroupMessage(final String groupId, final String name, final String content, final UpdateListener listener) {
        GroupTableMessage groupTableMessage = new GroupTableMessage();
        groupTableMessage.setObjectId(groupId);
        switch (name) {
            case ConstantUtil.GROUP_AVATAR:
                groupTableMessage.setGroupAvatar(content);
                break;
            case ConstantUtil.GROUP_NOTIFICATION:
                groupTableMessage.setNotification(content);
                break;
            case ConstantUtil.GROUP_DESCRIPTION:
                groupTableMessage.setGroupDescription(content);
                break;
            case ConstantUtil.GROUP_NAME:
                groupTableMessage.setGroupName(content);
                break;
            case ConstantUtil.GROUP_REMIND:
                groupTableMessage.setRemind(Boolean.parseBoolean(content));
                break;
            default:
                break;
        }
        groupTableMessage.update(listener);
    }


    public void getAllDefaultWallPaperFromServer(final FindListener<String> findListener) {
        BmobQuery bmobQuery = new BmobQuery("sys_data");
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        bmobQuery.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                if (e == null) {
                    List<String> avatarList = null;
                    if (jsonArray != null && jsonArray.length() > 0) {
                        LogUtil.e(jsonArray.toString());
                        avatarList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject avatarJson = jsonObject.getJSONObject("wallpaper");
                                String avatar = avatarJson.getString("url");
                                if (avatar != null) {
                                    avatarList.add(avatar);
                                }
                            } catch (JSONException item) {
                                item.printStackTrace();
                            }
                        }
                    }
                    if (avatarList == null) {
                        LogUtil.e("�?务器上�?�没有背景数�?�");
                    }
                    findListener.done(avatarList, null);
                } else {
                    findListener.done(null, e);
                }
            }


        });
    }

    public void getAllDefaultTitleWallPaperFromServer(final FindListener<String> findListener) {
        BmobQuery bmobQuery = new BmobQuery("sys_data");
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        bmobQuery.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                if (e == null) {
                    List<String> avatarList = null;
                    if (jsonArray != null && jsonArray.length() > 0) {
                        LogUtil.e(jsonArray.toString());
                        avatarList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {


                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject avatarJson = jsonObject.getJSONObject("twallpaper");
                                String avatar = avatarJson.getString("url");
                                if (avatar != null) {
                                    avatarList.add(avatar);
                                }
                            } catch (JSONException item) {
                                item.printStackTrace();
                            }
                        }
                    }
                    if (avatarList == null) {
                        LogUtil.e("�?务器上�?�没有背景数�?�");
                    }
                    findListener.done(avatarList, null);
                } else {
                    findListener.done(null, e);
                }
            }

        });
    }


    public Subscription getAllPostData(boolean isPublic, boolean isRefresh, String uid, String time, FindListener<PublicPostBean> findCallback) {
        BmobQuery<PublicPostBean> query = new BmobQuery<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = 0;
        try {
            currentTime = simpleDateFormat.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtil.e("现在的时间:" + currentTime);
        BmobDate bmobDate;
        if (isRefresh) {
            bmobDate = new BmobDate(new Date(currentTime));
            String key = ConstantUtil.UPDATE_TIME_SHARE + uid;
            if (isPublic) {
                key += ConstantUtil.PUBLIC;
            }
            String updateTime = BaseApplication
                    .getAppComponent()
                    .getSharedPreferences()
                    .getString(key, null);
            if (updateTime != null && !time.equals("0000-00-00 01:00:00")) {
                //                这里有个Bug,WhereGreaterThan 也查出相等时间的消�?�,所以在这里加上一秒的时间
                long resultTime = TimeUtil.getTime(updateTime, "yyyy-MM-dd HH:mm:ss") + 1000;
                query.addWhereGreaterThan("updatedAt", new BmobDate(new Date(resultTime)));
            } else {
                query.addWhereGreaterThan("updatedAt", bmobDate);
            }
            query.addWhereGreaterThanOrEqualTo("createdAt", bmobDate);
        } else {
            currentTime -= 1000;
            LogUtil.e("�?一秒�?�的时间" + currentTime);
            bmobDate = new BmobDate(new Date(currentTime));
            query.addWhereLessThan("createdAt", bmobDate);
        }
        if (!isPublic) {
            User user = new User();
            user.setObjectId(uid);
            query.addWhereEqualTo("author", new BmobPointer(user));
        }
        query.order("-createdAt");
        query.include("author");
        query.setLimit(10);
        query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ONLY);
        return query.findObjects(findCallback);
    }

    public Subscription sendPublicPostMessage(int type, String content, String location, final ArrayList<SystemUtil.ImageItem> imageList,
                                              String videoPath, String shotScreen, final PublicPostBean postBean, final OnCreatePublicPostListener listener) {
        final PublicPostBean publicPostBean = new PublicPostBean();
        publicPostBean.setMsgType(type);
        publicPostBean.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
        final PostDataBean postDataBean = new PostDataBean();
        postDataBean.setContent(content);
        publicPostBean.setLocation(location);
        publicPostBean.setAuthor(UserManager.getInstance().getCurrentUser());
        final Gson gson = BaseApplication
                .getAppComponent().getGson();
        if (type == ConstantUtil.EDIT_TYPE_SHARE) {
            postDataBean.setShareContent(gson.toJson(MsgManager.getInstance().cover(postBean)));
        } else if (type == ConstantUtil.EDIT_TYPE_IMAGE) {
            List<String> photoUrls = new ArrayList<>();
            if (imageList != null && imageList.size() > 0) {
                CommonLogger.e("�?��?的全部path为:");
                for (SystemUtil.ImageItem imageItem :
                        imageList) {
                    photoUrls.add(SystemUtil.sizeCompress(imageItem.getPath(), 1080, 1920));
                    CommonLogger.e(imageItem.getPath());
                }
                postDataBean.setImageList(photoUrls);
            }
        } else if (type == ConstantUtil.EDIT_TYPE_VIDEO) {
            List<String> photoUrls = new ArrayList<>();
            photoUrls.add(shotScreen);
            photoUrls.add(videoPath);
            postDataBean.setImageList(photoUrls);
        }
        publicPostBean.setContent(gson.toJson(postDataBean));
        listener.onSuccess(publicPostBean);
        return null;
    }

    public Subscription getCommentListData(String postId, FindListener<PublicCommentBean> listener, boolean isPullRefresh, String time) {
        PublicPostBean publicPostBean = new PublicPostBean();
        publicPostBean.setObjectId(postId);
        BmobQuery<PublicCommentBean> query = new BmobQuery<>();
        query.addWhereEqualTo(ConstantUtil.POST, publicPostBean);
        long currentTime = TimeUtil.getTime(time);
        BmobDate bmobDate = new BmobDate(new Date(currentTime));
        if (isPullRefresh) {
            String key = ConstantUtil.UPDATE_TIME_COMMENT + postId;
            String updateTime = BaseApplication
                    .getAppComponent().getSharedPreferences()
                    .getString(key, null);
            if (updateTime != null && !time.equals("0000-00-00 01:00:00")) {
                //                这里有个Bug,WhereGreaterThan 也查出相等时间的消�?�,所以在这里加上一秒的时间
                long resultTime = TimeUtil.getTime(updateTime, "yyyy-MM-dd HH:mm:ss") + 1000;
                query.addWhereGreaterThan("updatedAt", new BmobDate(new Date(resultTime)));
            } else {
                query.addWhereGreaterThan("updatedAt", bmobDate);
            }
            query.addWhereGreaterThanOrEqualTo("createdAt", bmobDate);
        } else {
            currentTime -= 1000;
            LogUtil.e("�?一秒�?�的时间" + currentTime);
            bmobDate = new BmobDate(new Date(currentTime));
            query.addWhereLessThan("createdAt", bmobDate);
        }
        query.order("-createdAt");
        query.include("user,post");
        query.setLimit(10);
        return query.findObjects(listener);
    }

    public void getCommentListDetailData(String publicId, FindListener<ReplyCommentListBean> listener) {
        BmobQuery<ReplyCommentListBean> query = new BmobQuery<>();
        query.addWhereEqualTo("publicId", publicId);
        query.setLimit(50);
        query.findObjects(listener);
    }


    public PublicCommentBean cover(PostCommentEntity entity) {
        DaoSession daoSession = UserDBManager.getInstance().getDaoSession();
        PublicCommentBean posterComments = new PublicCommentBean();
        posterComments.setObjectId(entity.getCid());
        posterComments.setSendStatus(entity.getSendStatus());
        posterComments.setContent(entity.getContent());
        posterComments.setCreatedTime(TimeUtil.getTime(entity.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
        posterComments.setUpdatedAt(TimeUtil.getTime(entity.getUpdatedTime(), "yyyy-MM-dd HH:mm:ss"));
        PublicPostEntity publicPostEntity = null;
        publicPostEntity =
                daoSession
                        .getPublicPostEntityDao()
                        .queryBuilder().where(PublicPostEntityDao.Properties.Pid.eq(entity.getPid())).build().list().get(0);
        PublicPostBean publicPostBean = cover(publicPostEntity);
        posterComments.setPost(publicPostBean);
        User commentUser;
        if (!entity.getUid().equals(UserManager.getInstance().getCurrentUserObjectId())) {
            commentUser = UserManager.getInstance().cover(daoSession
                    .getUserEntityDao()
                    .queryBuilder()
                    .where(UserEntityDao.Properties.Uid.eq(entity.getUid())).build().list().get(0));
        } else {
            commentUser = UserManager.getInstance().getCurrentUser();
        }
        posterComments.setUser(commentUser);
        return posterComments;
    }


    public PublicPostBean cover(PublicPostEntity posterMessageEntity) {
        User user = UserManager.getInstance().cover(UserDBManager.getInstance().getUser(posterMessageEntity.getUid()));
        PublicPostBean posterMessage = new PublicPostBean();
        posterMessage.setAuthor(user);
        posterMessage.setCreateTime(TimeUtil.getTime(posterMessageEntity.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
        posterMessage.setUpdatedAt(TimeUtil.getTime(posterMessageEntity.getUpdatedTime(), "yyyy-MM-dd HH:mm:ss"));
        posterMessage.setCommentCount(posterMessageEntity.getCommentCount());
        posterMessage.setLikeCount(posterMessageEntity.getLikeCount());
        posterMessage.setContent(posterMessageEntity.getContent());
        posterMessage.setLocation(posterMessageEntity.getLocation());
        posterMessage.setLikeList(posterMessageEntity.getLikeList());
        posterMessage.setMsgType(posterMessageEntity.getMsgType());
        posterMessage.setShareCount(posterMessageEntity.getShareCount());
        posterMessage.setObjectId(posterMessageEntity.getPid());
        return posterMessage;
    }

    public PublicPostEntity cover(PublicPostBean bean) {
        PublicPostEntity entity = new PublicPostEntity();
        entity.setSendStatus(bean.getSendStatus());
        entity.setCommentCount(bean.getCommentCount());
        entity.setLikeCount(bean.getLikeCount());
        entity.setContent(bean.getContent());
        entity.setLocation(bean.getLocation());
        entity.setLikeList(bean.getLikeList());
        entity.setPid(bean.getObjectId());
        entity.setUid(bean.getAuthor().getObjectId());
        entity.setMsgType(bean.getMsgType());
        entity.setShareCount(bean.getShareCount());
        entity.setCreatedTime(TimeUtil.getTime(bean.getCreatedAt(), "yyyy-MM-dd HH:mm:ss"));
        if (bean.getUpdatedAt() != null) {
            entity.setUpdatedTime(TimeUtil.getTime(bean.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss"));
        }
        return entity;
    }

    public Subscription reSendPublicPostBean(PublicPostBean data, OnCreatePublicPostListener listener) {
        PostDataBean bean = BaseApplication.getAppComponent().getGson().fromJson(data.getContent(), PostDataBean.class);
        if (data.getMsgType() == ConstantUtil.EDIT_TYPE_IMAGE) {
            List<String> imageList = bean.getImageList();
            if (imageList != null && imageList.size() > 0) {

                BmobFile.uploadBatch(imageList.toArray(new String[]{}), new UploadBatchListener() {
                    @Override
                    public void onSuccess(List<BmobFile> list2, List<String> list1) {
                        if (imageList.size() == list1.size()) {
                            CommonLogger.e("11全部上传图片�?功");
                            bean.setImageList(list1);
                            data.setContent(BaseApplication.getAppComponent().getGson().toJson(bean));
                            data.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
                            data.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    bean.setImageList(imageList);
                                    data.setContent(BaseApplication.getAppComponent().getGson().toJson(bean));
                                    if (e == null) {
                                        data.setObjectId(s);
                                        listener.onSuccess(data);
                                    } else {
                                        listener.onFailed(e.getMessage(), e.getErrorCode(), data);
                                    }
                                }

                            });
                        } else {
                            CommonLogger.e("目�?得到的URL集�?�为:");
                            for (String url :
                                    list1) {
                                CommonLogger.e(url);
                            }
                        }
                    }


                    @Override
                    public void onProgress(int i, int i1, int i2, int i3) {

                    }

                    @Override
                    public void onError(int i, String s) {
                        listener.onFailed(s, i, data);
                    }
                });
                return null;
            }
        } else if (data.getMsgType() == ConstantUtil.EDIT_TYPE_VIDEO) {
            List<String> stringList = new ArrayList<>(bean.getImageList());
            BmobFile.uploadBatch(bean.getImageList().toArray(new String[]{}), new UploadBatchListener() {
                @Override
                public void onSuccess(List<BmobFile> list2, List<String> list1) {
                    if (bean.getImageList().size() == list1.size()) {
                        CommonLogger.e("全部上传视频�?功");
                        bean.setImageList(list1);
                        data.setContent(BaseApplication.getAppComponent().getGson().toJson(bean));
                        data.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
                        data.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                bean.setImageList(stringList);
                                data.setContent(BaseApplication.getAppComponent().getGson().toJson(bean));
                                if (e == null) {
                                    data.setObjectId(s);
                                    listener.onSuccess(data);
                                } else {
                                    listener.onFailed(e.getMessage(), e.getErrorCode(), data);
                                }
                            }

                        });
                    } else {
                        CommonLogger.e("目�?得到的URL集�?�为:");
                        for (String url :
                                list1) {
                            CommonLogger.e(url);
                        }
                    }
                }


                @Override
                public void onProgress(int i, int i1, int i2, int i3) {

                }

                @Override
                public void onError(int i, String s) {
                    listener.onFailed(s, i, data);
                }
            });
            return null;

        } else if (data.getMsgType() == ConstantUtil.EDIT_TYPE_TEXT
                ) {
            data.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
            return data.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        listener.onSuccess(data);
                    } else {
                        listener.onFailed(e.getMessage(), e.getErrorCode(), data);
                    }
                }
            });
        } else if (data.getMsgType() == ConstantUtil.EDIT_TYPE_SHARE) {
            data.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
            return data.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        PublicPostBean temp = new PublicPostBean();
                        temp.increment("shareCount");
                        PublicPostEntity publicPostEntity = gson.fromJson(bean.getShareContent(), PublicPostEntity.class);
                        temp.update(publicPostEntity.getPid(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e != null) {
                                    CommonLogger.e("更新转�?�个数失败");
                                } else {
                                    CommonLogger.e("更新转�?�个数�?功");
                                    publicPostEntity.setShareCount(publicPostEntity.getShareCount() + 1);
                                    bean.setShareContent(gson.toJson(publicPostEntity));
                                    data.setContent(BaseApplication.getAppComponent().getGson().toJson(bean));
                                }
                                MsgManager.getInstance().sendPostNotifyInfo(ConstantUtil.TYPE_SHARE, data.getObjectId(), UserManager.getInstance()
                                        .getCurrentUserObjectId(), getUidForShareContent(bean.getShareContent()));
                                listener.onSuccess(data);
                            }
                        });
                    } else {
                        listener.onFailed(e.getMessage(), e.getErrorCode(), data);
                    }
                }
            });
        }
        return null;
    }

    private void sendPostShareNotifyInfo(String postId, String userObjectId, String toId) {

    }

    private String getUidForShareContent(String shareContent) {
        PublicPostBean publicPostBean = MsgManager.getInstance().cover(gson.fromJson(shareContent, PublicPostEntity.class));
        return publicPostBean.getAuthor().getObjectId();
    }

    public Subscription updatePublicPostBean(PublicPostBean data, OnCreatePublicPostListener listener) {
        PostDataBean bean = BaseApplication.getAppComponent().getGson().fromJson(data.getContent(), PostDataBean.class);
        if (data.getMsgType() == ConstantUtil.EDIT_TYPE_IMAGE
                ) {
            int size = bean.getImageList().size();
            List<String> upLoad = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                String item = bean.getImageList().get(i);
                if (item.startsWith("http")
                        || item.startsWith(ConstantUtil.IMAGE_COMPRESS_DIR)) {
                    //                    没有改�?�
                } else {
                    upLoad.add(bean.getImageList().get(i));
                }
            }
            List<String> resultList = new ArrayList<>(bean.getImageList());
            if (upLoad.size() > 0) {
                BmobFile.uploadBatch(upLoad.toArray(new String[]{}), new UploadBatchListener() {
                    @Override
                    public void onSuccess(List<BmobFile> list, List<String> list1) {
                        if (upLoad.size() == list1.size()) {
                            int size = list.size();
                            for (int i = 0; i < size; i++) {
                                CommonLogger.e("上传文件本地" + list.get(i).getLocalFile().getAbsolutePath()
                                        + "云端:" + list.get(i).getUrl());
                                for (int j = 0; j < upLoad.size(); j++) {
                                    if (upLoad.get(j).equals(list.get(i).getLocalFile().getAbsolutePath())) {
                                        bean.getImageList().set(bean.getImageList().indexOf(upLoad.get(j)), list.get(i).getUrl());
                                    }
                                }
                            }
                            if (!data.getSendStatus().equals(ConstantUtil.SEND_STATUS_SUCCESS)) {
                                data.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
                            }
                            data.setContent(BaseApplication.getAppComponent().getGson().toJson(bean));
                            data.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    bean.setImageList(resultList);
                                    data.setContent(BaseApplication.getAppComponent().getGson().toJson(bean));
                                    if (e == null) {
                                        listener.onSuccess(data);
                                    } else {
                                        listener.onFailed(e.getMessage(), e.getErrorCode(), data);
                                    }
                                }
                            });

                        }
                    }

                    @Override
                    public void onProgress(int i, int i1, int i2, int i3) {

                    }

                    @Override
                    public void onError(int i, String s) {
                        listener.onFailed(s, i, data);
                    }
                });
                return null;
            } else {
                data.setContent(BaseApplication.getAppComponent().getGson().toJson(bean));
                if (!data.getSendStatus().equals(ConstantUtil.SEND_STATUS_SUCCESS)) {
                    data.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
                }
                return data.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            listener.onSuccess(data);
                        } else {
                            listener.onFailed(e.getMessage(), e.getErrorCode(), data);
                        }
                    }
                });
            }
        } else if (data.getMsgType() == ConstantUtil.EDIT_TYPE_TEXT
                || data.getMsgType() == ConstantUtil.EDIT_TYPE_SHARE
                || data.getMsgType() == ConstantUtil.EDIT_TYPE_VIDEO) {
            data.setContent(BaseApplication.getAppComponent().getGson().toJson(bean)
            );
            if (!data.getSendStatus().equals(ConstantUtil.SEND_STATUS_SUCCESS)) {
                data.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
            }
            return data.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        listener.onSuccess(data);
                    } else {
                        listener.onFailed(e.getMessage(), e.getErrorCode(), data);
                    }
                }
            });
        } else {
            return null;
        }
    }

    public GroupTableEntity cover(GroupTableMessage groupTableMessage) {
        GroupTableEntity groupTableEntity = new GroupTableEntity();
        groupTableEntity.setCreatedTime(groupTableMessage.getCreatedTime());
        groupTableEntity.setCreatorId(groupTableMessage.getCreatorId());
        groupTableEntity.setGroupAvatar(groupTableMessage.getGroupAvatar());
        groupTableEntity.setGroupDescription(groupTableMessage.getGroupDescription());
        groupTableEntity.setGroupId(groupTableMessage.getGroupId());
        groupTableEntity.setGroupName(groupTableMessage.getGroupName());
        groupTableEntity.setGroupNumber(groupTableMessage.getGroupNumber());
        groupTableEntity.setNotification(groupTableMessage.getNotification());
        groupTableEntity.setReadStatus(groupTableMessage.getReadStatus());
        groupTableEntity.setSendStatus(groupTableMessage.getSendStatus());
        groupTableEntity.setToId(groupTableMessage.getToId());
        return groupTableEntity;
    }

    public ChatMessageEntity cover(ChatMessage message) {
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setBelongId(message.getBelongId());
        chatMessageEntity.setContent(message.getContent());
        chatMessageEntity.setContentType(message.getContentType());
        chatMessageEntity.setConversationId(message.getConversationId());
        chatMessageEntity.setCreatedTime(message.getCreateTime());
        chatMessageEntity.setMessageType(message.getMessageType());
        chatMessageEntity.setReadStatus(message.getReadStatus());
        chatMessageEntity.setSendStatus(message.getSendStatus());
        chatMessageEntity.setToId(message.getToId());
        return chatMessageEntity;
    }

    public RecentMessageEntity coverRecentMessage(BaseMessage message) {
        RecentMessageEntity recentMessageEntity = new RecentMessageEntity();
        if (message instanceof ChatMessage) {
            recentMessageEntity.setType(RecentMessageEntity.TYPE_PERSON);
            if (message.getBelongId().equals(UserManager.getInstance()
                    .getCurrentUserObjectId())) {
                recentMessageEntity.setId(((ChatMessage) message).getToId());
            } else {
                recentMessageEntity.setId(message.getBelongId());
            }
        } else if (message instanceof GroupChatMessage) {
            recentMessageEntity.setType(RecentMessageEntity.TYPE_GROUP);
            recentMessageEntity.setId(((GroupChatMessage) message).getGroupId());
        }
        recentMessageEntity.setContent(message.getContent());
        recentMessageEntity.setSendStatus(message.getSendStatus());
        recentMessageEntity.setContentType(message.getContentType());
        recentMessageEntity.setCreatedTime(message.getCreateTime());
        return recentMessageEntity;
    }

    public GroupChatEntity cover(GroupChatMessage groupChatMessage) {
        GroupChatEntity groupChatEntity = new GroupChatEntity();
        groupChatEntity.setBelongId(groupChatMessage.getBelongId());
        groupChatEntity.setContent(groupChatMessage.getContent());
        groupChatEntity.setCreatedTime(groupChatMessage.getCreateTime());
        groupChatEntity.setGroupId(groupChatMessage.getGroupId());
        groupChatEntity.setContentType(groupChatMessage.getContentType());
        groupChatEntity.setSendStatus(groupChatMessage.getSendStatus());
        groupChatEntity.setReadStatus(groupChatMessage.getReadStatus());
        return groupChatEntity;
    }

    public void refreshGroupChatMessage(FindListener<GroupChatMessage> findListener) {
        MsgManager.getInstance().queryGroupChatMessage(UserDBManager.getInstance().getAllGroupId(), findListener);


    }

    public ChatMessage cover(ChatMessageEntity chatMessageEntity) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setReadStatus(chatMessageEntity.getReadStatus());
        chatMessage.setContentType(chatMessageEntity.getContentType());
        chatMessage.setContent(chatMessageEntity.getContent());
        chatMessage.setSendStatus(chatMessageEntity.getSendStatus());
        chatMessage.setCreateTime(chatMessageEntity.getCreatedTime());
        chatMessage.setBelongId(chatMessageEntity.getBelongId());
        chatMessage.setConversationId(chatMessageEntity.getConversationId());
        chatMessage.setMessageType(chatMessageEntity.getMessageType());
        chatMessage.setToId(chatMessageEntity.getToId());
        return chatMessage;
    }

    public GroupChatMessage cover(GroupChatEntity item) {
        GroupChatMessage groupChatMessage = new GroupChatMessage();
        groupChatMessage.setContentType(item.getContentType());
        groupChatMessage.setContent(item.getContent());
        groupChatMessage.setGroupId(item.getGroupId());
        groupChatMessage.setBelongId(item.getBelongId());
        groupChatMessage.setCreateTime(item.getCreatedTime());
        groupChatMessage.setReadStatus(item.getReadStatus());
        groupChatMessage.setSendStatus(item.getSendStatus());
        return groupChatMessage;
    }

    public Subscription sendChatMessage(ChatMessage message, OnCreateChatMessageListener listener) {
        MessageContent messageContent = gson.fromJson(message.getContent(), MessageContent.class);
        int contentType = message.getContentType();
        if (contentType != ConstantUtil.TAG_MSG_TYPE_TEXT) {
            List<String> urlList = messageContent.getUrlList();
            BmobFile.uploadBatch(urlList.toArray(new String[]{}), new UploadBatchListener() {
                @Override
                public void onSuccess(List<BmobFile> list, List<String> list1) {
                    if (urlList.size() == list1.size()) {
                        messageContent.setUrlList(list1);
                        message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
                        message.setContent(gson.toJson(messageContent));
                        message.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                messageContent.setUrlList(urlList);
                                message.setContent(gson.toJson(messageContent));
                                if (e == null) {
                                    findInstallation(message.getToId(), new FindListener<CustomInstallation>() {
                                        @Override
                                        public void done(List<CustomInstallation> list, BmobException e) {
                                            if (e == null && list != null
                                                    && list.size() > 0) {
                                                sendJsonMessage(list.get(0).getInstallationId(), createJsonMessage(message), null);
                                            }
                                        }
                                    });
                                    listener.onSuccess(message);
                                    //
                                } else {
                                    listener.onFailed(e.toString(), message);
                                }
                            }
                        });
                    }
                }

                @Override
                public void onProgress(int i, int i1, int i2, int i3) {

                }

                @Override
                public void onError(int i, String s) {
                    listener.onFailed(s + i, message);
                }
            });
            return null;
        } else {
            message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
            return message.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        findInstallation(message.getToId(), new FindListener<CustomInstallation>() {
                            @Override
                            public void done(List<CustomInstallation> list, BmobException e) {
                                if (e == null && list != null
                                        && list.size() > 0) {
                                    sendJsonMessage(list.get(0).getInstallationId(), createJsonMessage(message), null);
                                }
                            }
                        });
                        listener.onSuccess(message);
                    } else {
                        listener.onFailed(e.toString(), message);
                    }
                }
            });
        }
    }

    public Subscription sendGroupChatMessage(GroupChatMessage message, OnCreateChatMessageListener listener) {
        MessageContent messageContent = gson.fromJson(message.getContent(), MessageContent.class);
        int contentType = message.getContentType();
        if (contentType != ConstantUtil.TAG_MSG_TYPE_TEXT) {
            List<String> urlList = messageContent.getUrlList();
            BmobFile.uploadBatch(urlList.toArray(new String[]{}), new UploadBatchListener() {
                @Override
                public void onSuccess(List<BmobFile> list, List<String> list1) {
                    if (urlList.size() == list1.size()) {
                        messageContent.setUrlList(list1);
                        message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
                        message.setContent(gson.toJson(messageContent));
                        message.setTableName("g" + message.getGroupId());
                        message.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                messageContent.setUrlList(urlList);
                                message.setContent(gson.toJson(messageContent));
                                if (e == null) {
                                    listener.onSuccess(message);
                                } else {
                                    listener.onFailed(e.toString(), message);
                                }
                            }
                        });
                    }
                }

                @Override
                public void onProgress(int i, int i1, int i2, int i3) {

                }

                @Override
                public void onError(int i, String s) {
                    listener.onFailed(s + i, message);
                }
            });
            return null;
        } else {
            message.setTableName("g" + message.getGroupId());
            message.setSendStatus(ConstantUtil.SEND_STATUS_SUCCESS);
            return message.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        listener.onSuccess(message);
                    } else {
                        listener.onFailed(e.toString(), message);
                    }
                }
            });
        }
    }

    public void exitGroup(String groupId, String uid, UpdateListener updateListener) {
        GroupTableEntity groupTableEntity = UserDBManager.getInstance()
                .getGroupTableEntity(groupId);
        GroupTableMessage groupTableMessage = new GroupTableMessage();
        groupTableMessage.setObjectId(groupTableEntity.getGroupId());
        groupTableEntity.setGroupNumber(groupTableEntity.getGroupNumber());
        groupTableMessage.getGroupNumber().remove(uid);
        groupTableMessage.update(updateListener);

    }

    public GroupTableMessage cover(GroupTableEntity
                                           entity) {
        return null;
    }

    public PostCommentEntity cover(PublicCommentBean item) {
        PostCommentEntity entity = new PostCommentEntity();
        entity.setUid(item.getUser().getObjectId());
        entity.setPid(item.getPost().getObjectId());
        entity.setSendStatus(item.getSendStatus());
        entity.setContent(item.getContent());
        entity.setCreatedTime(TimeUtil.getTime(item.getCreatedAt(), "yyyy-MM-dd HH:mm:ss"));
        entity.setUpdatedTime(TimeUtil.getTime(item.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss"));
        entity.setCid(item.getObjectId());
        return entity;
    }

    public PublicCommentBean createPostCommentBean(PublicCommentBean publicCommentBean, PublicPostBean data, String content) {
        final CommentDetailBean commentDetailBean = new CommentDetailBean();
        commentDetailBean.setContent(content);
        if (publicCommentBean != null) {
            CommentDetailBean originBean = gson.fromJson(publicCommentBean.getContent(), CommentDetailBean.class);
            String publicId = publicCommentBean.getUser().getObjectId() + "&" +
                    UserManager.getInstance().getCurrentUserObjectId();
            List<ReplyDetailContent> list = originBean.getConversationList().get(publicId);
            if (list == null) {
                publicId = UserManager.getInstance().getCurrentUserObjectId() + "&" + publicCommentBean.getUser().getObjectId();
                list = originBean.getConversationList().get(publicId);
            }
            Map<String, List<ReplyDetailContent>> map = new HashMap<>();
            if (list == null) {
                //                首次�?�方对�?
                List<ReplyDetailContent> replyDetailContents = new ArrayList<>();
                ReplyDetailContent one = new ReplyDetailContent();
                one.setTime(TimeUtil.severToLocalTime(publicCommentBean.getCreatedAt()));
                one.setContent(originBean.getContent());
                one.setUid(publicCommentBean.getUser().getObjectId());
                replyDetailContents.add(one);
                ReplyDetailContent two = new ReplyDetailContent();
                two.setTime(System.currentTimeMillis());
                two.setUid(UserManager.getInstance().getCurrentUserObjectId());
                two.setContent(content);
                replyDetailContents.add(two);
                map.put(publicId, replyDetailContents);
            } else {
                ReplyDetailContent two = new ReplyDetailContent();
                two.setTime(System.currentTimeMillis());
                two.setContent(content);
                two.setUid(UserManager.getInstance().getCurrentUserObjectId());
                List<ReplyDetailContent> other = new ArrayList<>();
                other.addAll(list);
                other.add(two);
                map.put(publicId, other);
            }
            commentDetailBean.setConversationList(map);
            commentDetailBean.setPublicId(publicId);
            commentDetailBean.setReplyContent(originBean.getContent());
        }
        final PublicCommentBean newBean = new PublicCommentBean();
        newBean.setContent(gson.toJson(commentDetailBean));
        newBean.setUser(UserManager.getInstance().getCurrentUser());
        newBean.setPost(data);
        newBean.setCreateTime(TimeUtil.getTime(TimeUtil.localToServerTime(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss"));
        newBean.setUpdatedAt(TimeUtil.getTime(TimeUtil.localToServerTime(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss"));
        return newBean;
    }

    public Subscription sendNotifyCommentInfo(PublicCommentBean newBean) {
        CommentDetailBean commentDetailBean = gson.fromJson(newBean.getContent()
                , CommentDetailBean.class);

        StringBuilder stringBuilder = new StringBuilder();

        if (!newBean.getPost().getAuthor().getObjectId().equals(UserManager.getInstance()
                .getCurrentUserObjectId())) {
            stringBuilder.append(newBean.getPost().getAuthor().getObjectId());
        }
        if (commentDetailBean.getPublicId() != null) {
            //                        回�?评论
            String[] str = commentDetailBean.getPublicId().split("&");
            String otherUid;
            if (str[0].equals(newBean.getPost().getAuthor().getObjectId())) {
                otherUid = str[1];
            } else {
                otherUid = str[0];
            }
            stringBuilder.append("&");
            stringBuilder.append(otherUid);
        }
        return MsgManager.getInstance().sendPostNotifyInfo(ConstantUtil.TYPE_COMMENT, newBean.getObjectId(), UserManager.getInstance().getCurrentUserObjectId()
                , stringBuilder.toString());
    }

    private String createNotifyInfo(Integer type, String id) {
        PostNotifyInfo postNotifyInfo = new PostNotifyInfo();
        postNotifyInfo.setId(id);
        postNotifyInfo.setType(type);
        postNotifyInfo.setReadStatus(ConstantUtil.READ_STATUS_UNREAD);
        return gson.toJson(postNotifyInfo);
    }


    public void getCommentBean(String commentId, FindListener<PublicCommentBean> findListener) {
        BmobQuery<PublicCommentBean> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("objectId", commentId);
        bmobQuery.findObjects(findListener);
    }

    public void updateCommentReadStatus(List<CommentNotifyBean> result) {
        for (CommentNotifyBean bean :
                result) {
            bean.setReadStatus(ConstantUtil.READ_STATUS_READED);
        }
        List<BmobObject> bmobObjectList = new ArrayList<>(result);
        new BmobBatch().updateBatch(bmobObjectList).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> list, BmobException e) {
                if (e == null) {
                    CommonLogger.e("批�?更新评论已读�?功");
                } else {
                    CommonLogger.e("批�?更新评论已读失败" + e.toString());
                }
            }
        });
    }

    public void updateCommentReadStatus(PublicCommentBean publicCommentBean, UpdateListener listener) {

    }

    public void updateSystemNotifyReadStatus(String id, UpdateListener listener) {
        SystemNotifyBean systemNotifyBean = new SystemNotifyBean();
        systemNotifyBean.setObjectId(id);
        systemNotifyBean.setReadStatus(ConstantUtil.READ_STATUS_READED);
        systemNotifyBean.update(listener);
    }

    public Subscription sendPostNotifyInfo(Integer type, String id, String uid, String toId) {
        if (type.equals(ConstantUtil.TYPE_LIKE) || type.equals(ConstantUtil.TYPE_SHARE)) {
            PostNotifyBean postNotifyBean = new PostNotifyBean();
            postNotifyBean.setType(type);
            postNotifyBean.setReadStatus(ConstantUtil.READ_STATUS_UNREAD);
            User user = new User();
            user.setObjectId(toId);
            postNotifyBean.setToUser(user);
            User relatedUser = new User();
            relatedUser.setObjectId(uid);
            postNotifyBean.setRelatedUser(relatedUser);
            PublicPostBean publicPostBean = new PublicPostBean();
            publicPostBean.setObjectId(id);
            postNotifyBean.setPublicPostBean(publicPostBean);
            return postNotifyBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        BmobQuery<User> bmobQuery = new BmobQuery<>();
                        bmobQuery.addWhereEqualTo("objectId", toId);
                        bmobQuery.findObjects(new FindListener<User>() {
                            @Override
                            public void done(List<User> list, BmobException e) {
                                if (e == null) {
                                    if (list != null && list.size() > 0) {
                                        sendJsonMessage(list.get(0).getInstallId(), createNotifyInfo(type, postNotifyBean.getObjectId()), new PushListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if (e == null) {
                                                    CommonLogger.e("推�?帖�?相关通知信�?��?功");
                                                } else {
                                                    CommonLogger.e("推�?帖�?相关通知信�?�失败" + e.toString());
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                }
            });
        } else if (type.equals(ConstantUtil.TYPE_COMMENT)) {
            List<String> list = new ArrayList<>();
            if (toId.contains("&")) {
                list = Arrays.asList(toId.split("&"));
            } else {
                list.add(toId);
            }
            List<BmobObject> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                PublicCommentBean publicCommentBean = new PublicCommentBean();
                publicCommentBean.setObjectId(id);
                PostNotifyBean postNotifyBean = new PostNotifyBean();
                postNotifyBean.setPublicCommentBean(publicCommentBean);
                postNotifyBean.setReadStatus(ConstantUtil.READ_STATUS_UNREAD);
                postNotifyBean.setType(type);
                User toUser = new User();
                toUser.setObjectId(list.get(0));
                postNotifyBean.setToUser(toUser);
                User relatedUser = new User();
                relatedUser.setObjectId(uid);
                postNotifyBean.setRelatedUser(relatedUser);
                list1.add(postNotifyBean);
            }
            List<String> finalList = list;
            new BmobBatch().insertBatch(list1).doBatch(new QueryListListener<BatchResult>() {
                @Override
                public void done(List<BatchResult> batchResults, BmobException e) {
                    if (e == null) {
                        CommonLogger.e("批�?添加评论通知�?功");
                        BmobQuery<User> userBmobQuery = new BmobQuery<>();
                        userBmobQuery.addWhereContainedIn("objectId", finalList);
                        userBmobQuery.findObjects(new FindListener<User>() {
                            @Override
                            public void done(List<User> list, BmobException e) {
                                if (e == null) {
                                    if (list != null && list.size() > 0) {
                                        List<String> installIdList = new ArrayList<>(list.size());
                                        for (User item :
                                                list) {
                                            installIdList.add(item.getInstallId());
                                        }
                                        StringBuilder stringBuilder = new StringBuilder();

                                        if (toId.contains("&")) {
                                            stringBuilder.append(batchResults.get(0).getObjectId()).append("&").append(batchResults
                                                    .get(1).getObjectId());
                                        } else {
                                            stringBuilder.append(batchResults.get(0).getObjectId());
                                        }
                                        sendJsonMessage(installIdList, createNotifyInfo(type, stringBuilder.toString()), new PushListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if (e == null) {
                                                    CommonLogger.e("推�?帖�?相关通知信�?��?功");
                                                } else {
                                                    CommonLogger.e("推�?帖�?相关通知信�?�失败" + e.toString());
                                                }
                                            }
                                        });

                                    }
                                }
                            }
                        });
                    } else {
                        CommonLogger.e("批�?添加评论通知失败" + e.toString());
                    }
                }
            });
        }
        return null;
    }

    public void updatePostNotifyReadStatus(PostNotifyInfo postNotifyInfo, FindListener<PostNotifyBean> listener) {
        BmobQuery<PostNotifyBean> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("readStatus", ConstantUtil.READ_STATUS_UNREAD);
        bmobQuery.addWhereEqualTo("toUser", UserManager.getInstance().getCurrentUser());
        if (postNotifyInfo.getType().equals(ConstantUtil.TYPE_LIKE) || postNotifyInfo
                .getType().equals(ConstantUtil.TYPE_SHARE)) {
            bmobQuery.addWhereEqualTo("objectId", postNotifyInfo.getId());
        } else {
            if (postNotifyInfo.getId().contains("&")) {
                bmobQuery.addWhereContainedIn("objectId", Arrays.asList(postNotifyInfo.getId().split("&")));
            } else {
                bmobQuery.addWhereEqualTo("objectId", postNotifyInfo.getId());
            }
        }
        bmobQuery.include("relatedUser");
        bmobQuery.findObjects(new FindListener<PostNotifyBean>() {
            @Override
            public void done(List<PostNotifyBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        UserDBManager.getInstance().addOrUpdateUser(list.get(0).getRelatedUser());
                        list.get(0).setReadStatus(ConstantUtil.READ_STATUS_READED);
                        list.get(0).update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                listener.done(list, e);
                            }
                        });
                    } else {
                        listener.done(null, null);
                    }
                } else {
                    listener.done(null, e);
                }
            }
        });
    }

    public SkinEntity cover(SkinBean bean) {
        List<SkinEntity> list = UserDBManager.getInstance()
                .getDaoSession().getSkinEntityDao().queryBuilder()
                .where(SkinEntityDao.Properties.Url.eq(bean.getUrl()))
                .build().list();
        if (list.size() > 0) {
            list.get(0).setImageList(bean.getImageList());
            list.get(0).setTitle(bean.getTitle());
            UserDBManager.getInstance().getDaoSession().getSkinEntityDao()
                    .update(list.get(0));
            return list.get(0);
        } else {
            SkinEntity skinEntity = new SkinEntity();
            skinEntity.setTitle(bean.getTitle());
            skinEntity.setImageList(bean.getImageList());
            skinEntity.setHasSelected(false);
            skinEntity.setUrl(bean.getUrl());
            UserDBManager.getInstance().getDaoSession().getSkinEntityDao()
                    .insert(skinEntity);
            return skinEntity;
        }
    }

    public StepData cover(StepBean stepBean) {
        StepData stepData = new StepData();
        stepData.setUid(stepBean.getUser().getObjectId());
        stepData.setTime(stepBean.getTime());
        stepData.setId(stepBean.getObjectId());
        stepData.setStepCount(stepBean.getStepCount());
        return stepData;
    }

    public void saveCurrentStep(int stepNumber) {
        StepData stepData = UserDBManager.getInstance().getStepData(TimeUtil.getCurrentTime());
        if (stepData != null) {
            stepData.setStepCount(stepNumber);
            UserDBManager.getInstance().getDaoSession().update(stepData);
        }
    }
}

