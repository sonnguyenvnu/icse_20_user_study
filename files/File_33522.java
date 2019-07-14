package com.example.jingbin.cloudreader.data.room;

import android.support.annotation.NonNull;

import com.example.jingbin.cloudreader.utils.AppExecutors;
import com.example.jingbin.cloudreader.utils.DebugUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author jingbin
 * @data 2018/4/19
 * @Description
 */

public class UserDataBaseSource {

    private static volatile UserDataBaseSource INSTANCE;
    private UserDao mUserDao;
    private AppExecutors mAppExecutors;

    private UserDataBaseSource(@NonNull AppExecutors mAppExecutors, @NonNull UserDao mUserDao) {
        this.mAppExecutors = mAppExecutors;
        this.mUserDao = mUserDao;
    }

    public static UserDataBaseSource getInstance(@NonNull AppExecutors appExecutors,
                                                 @NonNull UserDao tasksDao) {
        if (INSTANCE == null) {
            synchronized (UserDataBaseSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDataBaseSource(appExecutors, tasksDao);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * æŸ¥æ‰¾ä»»ä½•çš„bean(æ²¡æœ‰æ•°æ?®æ—¶ä¼šæŠ¥é”™ï¼?)ï¼š
     * å¦‚æžœæ•°æ?®åº“é‡Œæœ‰ä¸€æ?¡æ•°æ?®å°±è¿”å›žè¿™æ?¡æ•°æ?®
     * å¦‚æžœæœ‰å¤šæ?¡ä¿¡æ?¯ï¼Œåˆ™è¿”å›žç¬¬ä¸€æ?¡æ•°æ?®
     */
    public void getSingleBean(UserDataCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    User user = mUserDao.findSingleBean();
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            if (user == null) {
                                callback.onDataNotAvailable();
                            } else {
                                callback.getData(user);
                            }
                        }
                    });
                } catch (Exception e) {
                    DebugUtil.error(e.getMessage());
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    /**
     * å…ˆåˆ é™¤å?Žå†?æ·»åŠ : é‡?æ–°ç™»å½•æ—¶
     */
    public void addData(@NonNull User user) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int success = mUserDao.deleteAll();
                    DebugUtil.error("----success:" + success);
                    mUserDao.addUser(user);
                } catch (Exception e) {
                    DebugUtil.error(e.getMessage());
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }


    /**
     * æ›´æ–°æ•°æ?®
     */
    public void updateData(@NonNull User user) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    mUserDao.addUser(user);
                } catch (Exception e) {
                    DebugUtil.error(e.getMessage());
                }
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    /**
     * æ¸…é™¤æ•°æ?®åº“
     */
    public void deleteAllData() {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    mUserDao.deleteAll();
                } catch (Exception e) {
                    DebugUtil.error(e.getMessage());
                }
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    /**
     * èŽ·å?–æ•°æ?®é›†å?ˆ
     */
    public void getAll() {
        UserDataBase.getDatabase().waitDao().findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
//                        DebugUtil.error("----waitList.size():" + waits.size());
//                        DebugUtil.error("----waitList:" + waits.toString());
                    }
                });
    }
    /**
     * èŽ·å?–å…¨éƒ¨æ•°æ?®é›†å?ˆ
     */
    public void getAllData() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    List<User> waits = mUserDao.findUsers();
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                } catch (Exception e) {
                    DebugUtil.error(e.getMessage());
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}
