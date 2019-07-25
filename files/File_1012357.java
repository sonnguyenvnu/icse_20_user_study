package com.example.chat.manager;

/**
 * 项目�??称:    HappyChat
 * 创建人:        陈锦军
 * 创建时间:    2016/9/11      17:31
 * QQ:             1981367757
 */


import android.content.SharedPreferences;

import com.example.chat.base.ConstantUtil;
import com.example.chat.bean.ChatMessage;
import com.example.chat.bean.CustomInstallation;
import com.example.chat.listener.AddBlackCallBackListener;
import com.example.chat.listener.AddFriendCallBackListener;
import com.example.chat.listener.CancelBlackCallBlackListener;
import com.example.chat.listener.OnSendTagMessageListener;
import com.example.chat.util.LogUtil;
import com.example.chat.util.TimeUtil;
import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.bean.chat.User;
import com.example.commonlibrary.bean.chat.UserEntity;
import com.example.commonlibrary.utils.CommonLogger;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.PushListener;
import cn.bmob.v3.listener.UpdateListener;
import rx.Subscription;

/**
 * 用户管�?�类
 * 与用户�?作有关的�?作类：登录�?退出�?获�?�好�?�列表�?获�?�当�?的登录用户,删除好�?�，添加好�?�等
 */
public class UserManager {

    //        用于�?�步
    private static final Object INSTANCE_LOCK = new Object();
    private static UserManager INSTANCE;
    private String uid;

    public static UserManager getInstance() {
        if (INSTANCE == null) {
            synchronized (INSTANCE_LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new UserManager();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 如果没有自定义用户实体类extend BmobUser
     * 默认返回BmobUser类型的用户实体
     * 获�?�当�?用户
     *
     * @return 当�?用户
     */
    public User getCurrentUser() {
        return BmobUser.getCurrentUser(User.class);
    }


    /**
     * 获�?�当�?的好�?�列表和更新黑�??�?�好�?�并回调在callback
     *
     * @param callback 回调
     */
    public Subscription queryAndSaveCurrentContactsList(final FindListener<User> callback) {
        BmobQuery<User> query = new BmobQuery<>();
        query.order("-updatedAt");
        query.setLimit(ConstantUtil.LIMIT_CONTACTS);
        query.addWhereRelatedTo(ConstantUtil.COLUMN_NAME_CONTACTS, new BmobPointer(getCurrentUser()));
        return query.findObjects(new FindListener<User>() {
                                     @Override
                                     public void done(final List<User> friend, BmobException e) {
                                         if (e == null) {
                                             UserDBManager.getInstance().addOrUpdateContacts(friend);
                                             queryAddBlackList(new FindListener<User>() {
                                                                   @Override
                                                                   public void done(List<User> list, BmobException e) {
                                                                       if (e == null) {
                                                                           if (list != null && list.size() > 0) {
                                                                               UserDBManager.getInstance().addOrUpdateBlack(list, UserEntity.BLACK_TYPE_ADD);
                                                                           }
                                                                       } else {
                                                                           CommonLogger.e("在�?务器上查询添加黑�??�?�失败" + e.toString());
                                                                       }

                                                                   }
                                                               }
                                             );
                                             queryOtherBlackList(new FindListener<User>() {
                                                 @Override
                                                 public void done(List<User> list, BmobException e) {
                                                     if (e == null) {
                                                         if (list != null && list.size() > 0) {
                                                             UserDBManager.getInstance().addOrUpdateBlack(list, UserEntity.BLACK_TYPE_OTHER);
                                                         }
                                                     } else {
                                                         CommonLogger.e("在�?务器上查询其他黑�??�?�失败" + e.toString());
                                                     }
                                                 }
                                             });
                                         } else {
                                             LogUtil.e("在�?务器上查询好�?�失败" + e.toString());
                                         }
                                         callback.done(friend, e);
                                     }
                                 }
        );
    }

    private void queryOtherBlackList(FindListener<User> listener) {
        BmobQuery<User> query = new BmobQuery<>();
        User user = new User();
        user.setObjectId(UserManager.getInstance().getCurrentUserObjectId());
        query.addWhereRelatedTo(ConstantUtil.COLUMN_NAME_OTHER_BLACKLIST, new BmobPointer(user));
        query.findObjects(listener);
    }

    /**
     * 查询黑�??�?�用户
     *
     * @param callback 回调
     */

    private void queryAddBlackList(final FindListener<User> callback) {
        BmobQuery<User> query = new BmobQuery<>();
        query.order("updateAt");
        query.addWhereRelatedTo(ConstantUtil.COLUMN_NAME_ADD_BLACKLIST, new BmobPointer(getCurrentUser()));
        query.findObjects(callback);
    }


    /**
     * 获�?�本用户ID
     *
     * @return 用户ID
     */
    public String getCurrentUserObjectId() {
        if (uid == null) {
            User user = getCurrentUser();
            if (user != null) {
                uid = user.getObjectId();
            }
        }
        return uid;
    }


    /**
     * 根�?�用户�??查询用户
     *
     * @param name     根�?�用户�??在�?务器上查询用户
     * @param listener 回调
     */
    public Subscription queryUsers(String name, FindListener<User> listener) {
        BmobQuery<User> eq1 = new BmobQuery<>();
        eq1.addWhereEqualTo("username", name);
        BmobQuery<User> eq2 = new BmobQuery<>();
        eq2.addWhereEqualTo("name", name);
        List<BmobQuery<User>> queries = new ArrayList<>();
        queries.add(eq1);
        queries.add(eq2);
        BmobQuery<User> mainQuery = new BmobQuery<>();
        mainQuery.or(queries);
        return mainQuery.findObjects(listener);
    }


    /**
     * 下线�?作
     */
    public void logout() {
        List<String> list = UserDBManager.getInstance().getAllFriendId();
        list.add(uid);
        SharedPreferences.Editor edit = BaseApplication.getAppComponent().getSharedPreferences()
                .edit();
        for (String item :
                list) {
            edit.putString(ConstantUtil.UPDATE_TIME_SHARE + item, null);
        }
        edit.putBoolean(ConstantUtil.LOGIN_STATUS, false);
        edit.apply();
        uid = null;
        User.logOut();
        MobclickAgent.onProfileSignOff();
    }

    /**
     * 添加用户为好�?�(1�?在�?务器上添加关�?�，添加如数�?�库)
     *
     * @param targetId                  对方ID
     * @param currentId                 本用户ID
     * @param addFriendCallBackListener 回调
     */
    public void addNewFriend(final String targetId, final String currentId, @NonNull final AddFriendCallBackListener addFriendCallBackListener) {
        findUserById(targetId, new FindListener<User>() {
                    @Override
                    public void done(final List<User> list, BmobException e) {
                        if (e == null) {
                            if (list != null && list.size() > 0) {
                                saveNewFriendToServer(list.get(0), currentId, new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if (e == null) {
                                                    LogUtil.e("在�?务器上关�?�好�?��?功");
                                                    UserDBManager.getInstance(currentId).addOrUpdateUser(list.get(0));
                                                    addFriendCallBackListener.onSuccess(list.get(0));
                                                } else {
                                                    LogUtil.e("在�?务器上关�?�好�?�失败");
                                                    addFriendCallBackListener.onFailed(e);
                                                }
                                            }
                                        }
                                );
                            } else {
                                addFriendCallBackListener.onFailed(new BmobException("在�?务器上�?�有查到该用户"));
                            }
                        } else {
                            LogUtil.e("在�?务器上查询用户失败" + e.toString());
                            addFriendCallBackListener.onFailed(e);
                        }
                    }

                }
        );
    }

    /**
     * 在�?务器上关�?�该好�?�
     *
     * @param user           用户实体
     * @param currentId      现在的登录用户ID
     * @param updateListener 跟新回调
     */
    private void saveNewFriendToServer(User user, String currentId, UpdateListener updateListener) {
        if (getCurrentUser() != null && currentId.equals(getCurrentUserObjectId())) {
            User currentUser = new User();
            currentUser.setObjectId(currentId);
            BmobRelation relation = new BmobRelation();
            relation.add(user);
            currentUser.setContacts(relation);
            currentUser.update(updateListener
            );
        } else {
            LogUtil.e("toId：" + currentId);
            if (getCurrentUser() != null) {
                LogUtil.e("现在的UID：" + getCurrentUserObjectId());
            }
            //                        �?是当�?用户的情况下
            LogUtil.e("�?是当�?的用户，�?在�?务器上关�?�该好�?�");
            updateListener.done(new BmobException(0, "�?是当�?的用户，�?在�?务器上关�?�该好�?�"));
        }
    }

    /**
     * 根�?�用户ID获�?�用户信�?�
     *
     * @param uid          用户ID
     * @param findListener 回调
     */
    public Subscription findUserById(String uid, FindListener<User> findListener) {
        if (uid.equals(UserManager.getInstance().getCurrentUserObjectId())) {
            findListener.done(Collections.singletonList(UserManager.getInstance().getCurrentUser()), null);
            return null;
        }
        UserEntity userEntity = UserDBManager.getInstance().getUser(uid);
        if (userEntity == null) {
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("objectId", uid);
            return query.findObjects(new FindListener<User>() {
                @Override
                public void done(List<User> list, BmobException e) {
                    if (e == null) {
                        if (list != null && list.size() > 0) {
                            UserDBManager.getInstance()
                                    .addOrUpdateUser(cover(list.get(0), true));
                        }
                    }
                    if (findListener != null) {
                        findListener.done(list, e);
                    }
                }
            });
        } else {
            List<User> list1 = new ArrayList<>(1);
            list1.add(cover(userEntity));
            if (findListener != null) {
                findListener.done(list1, null);
            }
            return null;
        }
    }


    /**
     * 添加为黑�??�?�
     *
     * @param uid                      用户实体
     * @param addBlackCallBackListener 回调
     */
    public void addToBlack(String uid, final AddBlackCallBackListener addBlackCallBackListener) {
        addBlackRelation(uid, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            MsgManager.getInstance()
                                    .sendTagMessage(uid, ChatMessage.MESSAGE_TYPE_ADD_BLACK
                                            , new OnSendTagMessageListener() {
                                                @Override
                                                public void onSuccess(ChatMessage chatMessage) {
                                                    UserEntity userEntity = UserDBManager
                                                            .getInstance().getUser(uid);
                                                    userEntity.setIsBlack(true);
                                                    userEntity.setBlackType(UserEntity.BLACK_TYPE_ADD);
                                                    UserDBManager
                                                            .getInstance().getDaoSession()
                                                            .getUserEntityDao().update(userEntity);
                                                    UserDBManager.getInstance()
                                                            .deleteChatMessage(chatMessage.getConversationId()
                                                                    , ChatMessage.MESSAGE_TYPE_CANCEL_BLACK);
                                                    addBlackCallBackListener.onSuccess();
                                                }

                                                @Override
                                                public void onFailed(BmobException e) {
                                                    addBlackCallBackListener.onFailed(e);
                                                }
                                            });
                        } else {
                            LogUtil.e("在�?务器上关�?�该用户为黑�??�?�失败");
                            addBlackCallBackListener.onFailed(e);
                        }
                    }


                }
        );


    }

    private void addBlackRelation(String uid, UpdateListener listener) {
        User currentUser = new User();
        currentUser.setObjectId(getCurrentUserObjectId());
        BmobRelation relation = new BmobRelation();
        User friend = new User();
        friend.setObjectId(uid);
        relation.add(friend);
        currentUser.setAddBlack(relation);
        currentUser.update(listener);
    }

    /**
     * �?�消黑�??�?�回调
     *
     * @param uid      用户实体
     * @param listener 回调
     */
    public void cancelBlack(String uid, final CancelBlackCallBlackListener listener) {
        deleteBlackRelation(uid, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            MsgManager.getInstance()
                                    .sendTagMessage(uid, ChatMessage.MESSAGE_TYPE_CANCEL_BLACK, new OnSendTagMessageListener() {
                                        @Override
                                        public void onSuccess(ChatMessage chatMessage) {
                                            UserEntity userEntity = UserDBManager
                                                    .getInstance().getUser(uid);
                                            userEntity.setIsBlack(false);
                                            userEntity.setBlackType(0);
                                            UserDBManager
                                                    .getInstance().getDaoSession()
                                                    .getUserEntityDao().update(userEntity);
                                            UserDBManager.getInstance()
                                                    .deleteChatMessage(chatMessage.getConversationId()
                                                            , ChatMessage.MESSAGE_TYPE_ADD_BLACK);
                                            listener.onSuccess();
                                        }

                                        @Override
                                        public void onFailed(BmobException e) {
                                            listener.onFailed(e);
                                        }
                                    });
                        } else {
                            listener.onFailed(e);
                        }
                    }
                }
        );
    }

    private void deleteBlackRelation(String uid, UpdateListener listener) {
        User currentUser = new User();
        BmobRelation relation = new BmobRelation();
        User friend = new User();
        friend.setObjectId(uid);
        relation.remove(friend);
        currentUser.setAddBlack(relation);
        currentUser.setObjectId(getCurrentUserObjectId());
        currentUser.update(listener);
    }

    private void bindInstallation(final UpdateListener listener) {
        BmobQuery<CustomInstallation> query = new BmobQuery<>();
        query.addWhereEqualTo("installationId", new CustomInstallation().getInstallationId());
        query.findObjects(new FindListener<CustomInstallation>() {
            @Override
            public void done(final List<CustomInstallation> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        CustomInstallation installation = list.get(0);
                        installation.setUid(UserManager.getInstance().getCurrentUserObjectId());
                        installation.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    LogUtil.e("绑定设备表中UID�?功");
                                } else {
                                    LogUtil.e("绑定设备表中UID设备失败");
                                }
                                listener.done(e);
                            }
                        });
                    }
                } else {
                    LogUtil.e("在�?务器上查询设备失败" + e.toString());
                    listener.done(e);
                }
            }

        });
    }

    /**
     * 检查本设备表中的uid   ，如果有，就�?��?下线通知，�?作�?功�?�，�?把uid更新到本地的设备表中
     */
    public Subscription checkInstallation(final UpdateListener listener) {
        BmobQuery<CustomInstallation> query = new BmobQuery<>();
        CommonLogger.e("checkInstallation UID：" + getCurrentUserObjectId());
        query.addWhereEqualTo("uid", getCurrentUserObjectId());
        query.order("-updatedAt");
        return query.findObjects(new FindListener<CustomInstallation>() {
                                     @Override
                                     public void done(List<CustomInstallation> list, BmobException e) {
                                         if (e == null) {
                                             if (list != null && list.size() > 0) {
                                                 CustomInstallation customInstallation = list.get(0);
                                                 if (customInstallation.getInstallationId().equals(new CustomInstallation().getInstallationId())) {
                                                     CommonLogger.e("由于绑定的是本设备表，�?�?��?作所以");
                                                     listener.done(null);
                                                 } else {
                                                     //                                                        �?管推�?�?功与�?�，都�?更新设备表的UID
                                                     MsgManager.getInstance().sendOfflineNotificationMsg(customInstallation, new PushListener() {
                                                                 @Override
                                                                 public void done(BmobException e) {
                                                                     if (e == null) {
                                                                         LogUtil.e("推�?下线通知消�?��?功");
                                                                     } else {
                                                                         LogUtil.e("推�?下线通知消�?�失败" + e.toString());

                                                                     }
                                                                     bindInstallation(listener);
                                                                 }
                                                             }
                                                     );
                                                 }
                                             } else {
                                                 //                                          LogUtil.e("查询�?到本用户所对应的设备ID,这里新建一个设备表");
                                                 bindInstallation(listener);
                                             }
                                         } else {
                                             LogUtil.e("查询本用户对应的设备表出错" + e.toString());
                                             listener.done(e);
                                         }
                                     }
                                 }
        );
    }

    public void updateUserInfo(final String name, final String content, final UpdateListener listener) {
        User user = new User();
        user.setObjectId(UserManager.getInstance().getCurrentUserObjectId());
        switch (name) {
            case ConstantUtil.PHONE:
                user.setMobilePhoneNumber(content);
                break;
            case ConstantUtil.EMAIL:
                user.setEmail(content);
                break;
            case ConstantUtil.NICK:
                user.setNick(content);
                break;
            case ConstantUtil.AVATAR:
                user.setAvatar(content);
                break;
            case ConstantUtil.GENDER:
                if (content.equals("男")) {
                    user.setSex(true);
                } else {
                    user.setSex(false);
                }
                break;
            case ConstantUtil.SIGNATURE:
                user.setSignature(content);
                break;
            case ConstantUtil.BIRTHDAY:
                user.setBirthDay(content);
                break;
            case ConstantUtil.ADDRESS:
                user.setAddress(content);
                break;
            case ConstantUtil.LOCATION:
                LogUtil.e("定�?location" + content);
                String result[] = content.split("&");
                user.setLocation(new BmobGeoPoint(Double.parseDouble(result[0]), Double.parseDouble(result[1])));
                break;
            case ConstantUtil.TITLE_WALLPAPER:
                user.setTitleWallPaper(content);
                break;
            case ConstantUtil.WALLPAPER:
                user.setWallPaper(content);
                break;
            case ConstantUtil.INSTALL_ID:
                user.setInstallId(content);
                break;
            case ConstantUtil.NAME:
                user.setName(content);
                break;
        }
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    CommonLogger.e("用户信�?�更新�?功");
                } else {
                    CommonLogger.e("用户信�?�更新失败" + e.toString());
                }
                if (listener != null) {
                    listener.done(e);
                }
            }
        });
    }

    public void queryNearbyPeople(int num, int flag, FindListener<User> findListener) {
        User currentUser = getCurrentUser();
        BmobQuery<User> query = new BmobQuery<>();
        if (flag == 1) {
            query.addWhereEqualTo("sex", false);
        } else if (flag == 2) {
            query.addWhereEqualTo("sex", true);
        }
        double longitude;
        double latitude;
        if (currentUser.getLocation() != null) {
            longitude = currentUser.getLocation().getLongitude();
            latitude = currentUser.getLocation().getLatitude();
        } else {
            SharedPreferences sharedPreferences = BaseApplication.getAppComponent()
                    .getSharedPreferences();
            if (sharedPreferences.getString(ConstantUtil.LONGITUDE, null) == null) {
                findListener.done(null, new BmobException("定�?信�?�为空!!!!"));
                return;
            }
            longitude = Double.parseDouble(sharedPreferences.getString(ConstantUtil.LONGITUDE, null));
            latitude = Double.parseDouble(sharedPreferences.getString(ConstantUtil.LATITUDE, null));
            updateUserInfo(ConstantUtil.LOCATION, longitude + "&" + latitude, null);
        }
        query.addWhereNear("location", new BmobGeoPoint(longitude, latitude));
        query.addWhereNotEqualTo("objectId", currentUser.getObjectId());
        query.setSkip(num);
        query.setLimit(10);
        query.findObjects(findListener);
    }


    public void refreshUserInfo() {
        List<String> userList = UserDBManager.getInstance().getAllFriendId();
        String currentUserObjectId = getCurrentUserObjectId();
        if (userList != null && userList.size() > 0) {
            for (final String uid :
                    userList) {
                BmobQuery<User> query = new BmobQuery<>();

                String lastTime = BaseApplication
                        .getAppComponent()
                        .getSharedPreferences().getString(currentUserObjectId + "&" + uid, null);
                //                                        第一次断网查询用户数�?�
                query.addWhereGreaterThan("updatedAt", new BmobDate(new Date(TimeUtil.getTime(lastTime))));
                query.addWhereEqualTo("objectId", uid);
                query.findObjects(new FindListener<User>() {
                    @Override
                    public void done(List<User> list, BmobException e) {
                        if (e == null) {
                            if (list != null && list.size() > 0) {
                                User user = list.get(0);
                                BaseApplication.getAppComponent()
                                        .getSharedPreferences()
                                        .edit().putString(currentUserObjectId + "&" + user.getObjectId(), user.getUpdatedAt())
                                        .apply();
                                UserDBManager.getInstance().addOrUpdateUser(user);
                            }
                        } else {
                            LogUtil.e("断网期间内查询用户失败" + e.toString());
                        }
                    }
                });
            }
        }
    }


    public UserEntity cover(User currentUser, boolean isStranger) {
        return cover(currentUser, isStranger, false, UserEntity.BLACK_TYPE_NORMAL);
    }

    public UserEntity cover(User currentUser, boolean isStranger, boolean isBlack, int blackType) {
        UserEntity userEntity = new UserEntity();
        userEntity.setIsStranger(isStranger);
        userEntity.setTitlePaper(currentUser.getTitleWallPaper());
        userEntity.setUpdatedTime(currentUser.getUpdatedAt());
        userEntity.setCreatedTime(currentUser.getCreatedAt());
        userEntity.setSex(currentUser.isSex());
        userEntity.setAvatar(currentUser.getAvatar());
        userEntity.setNick(currentUser.getNick());
        userEntity.setUid(currentUser.getObjectId());
        userEntity.setAddress(currentUser.getAddress());
        userEntity.setPhone(currentUser.getMobilePhoneNumber());
        userEntity.setEmail(currentUser.getEmail());
        userEntity.setBirthDay(currentUser.getBirthDay());
        userEntity.setBlack(isBlack);
        userEntity.setBlackType(blackType);
        userEntity.setClassNumber(currentUser.getClassNumber());
        userEntity.setCollege(currentUser.getCollege());
        userEntity.setEducation(currentUser.getEducation());
        userEntity.setMajor(currentUser.getMajor());
        userEntity.setName(currentUser.getName());
        userEntity.setUserName(currentUser.getUsername());
        userEntity.setSchool(currentUser.getSchool());
        userEntity.setSignature(currentUser.getSignature());
        userEntity.setYear(currentUser.getYear());
        return userEntity;
    }


    public UserEntity cover(User user) {
        return cover(user, UserDBManager.getInstance().isStranger(user.getObjectId()));
    }


    public User cover(UserEntity userEntity) {
        User user = new User();
        user.setSex(userEntity.isSex());
        user.setAvatar(userEntity.getAvatar());
        user.setTitleWallPaper(userEntity.getTitlePaper());
        user.setCreatedTime(userEntity.getCreatedTime());
        user.setUpdatedAt(userEntity.getUpdatedTime());
        user.setNick(userEntity.getNick());
        user.setObjectId(userEntity.getUid());
        user.setMobilePhoneNumber(userEntity.getPhone());
        user.setEmail(userEntity.getEmail());
        user.setAddress(userEntity.getAddress());
        user.setBirthDay(userEntity.getBirthDay());
        user.setName(userEntity.getName());
        user.setUsername(userEntity.getUserName());
        user.setSchool(userEntity.getSchool());
        user.setCollege(userEntity.getCollege());
        user.setMajor(userEntity.getMajor());
        user.setEducation(userEntity.getEducation());
        user.setYear(userEntity.getYear());
        user.setClassNumber(userEntity.getClassNumber());
        user.setSignature(userEntity.getSignature());
        user.setCreatedTime(userEntity.getCreatedTime());
        user.setUpdatedAt(userEntity.getUpdatedTime());
        return user;
    }

}
