package com.example.chat.mvp.login.phone;

import com.example.chat.base.AppBasePresenter;
import com.example.chat.base.ConstantUtil;
import com.example.chat.base.RandomData;
import com.example.chat.bean.CustomInstallation;
import com.example.chat.bean.GroupTableMessage;
import com.example.chat.manager.MsgManager;
import com.example.chat.manager.UserDBManager;
import com.example.chat.manager.UserManager;
import com.example.chat.util.LogUtil;
import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.bean.BaseBean;
import com.example.commonlibrary.bean.chat.User;
import com.example.commonlibrary.mvp.model.DefaultModel;
import com.example.commonlibrary.mvp.view.IView;
import com.example.commonlibrary.utils.CommonLogger;
import com.example.commonlibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目�??称:    NewFastFrame
 * 创建人:      陈锦军
 * 创建时间:    2018/12/29     16:18
 */
public class PhoneLoginPresenter extends AppBasePresenter<IView<BaseBean>, DefaultModel> {
    public PhoneLoginPresenter(IView<BaseBean> iView, DefaultModel baseModel) {
        super(iView, baseModel);
    }

    public void getVerifyCode(String phone) {
        BmobSMS.requestSMSCode(phone, "login", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException ex) {
                BaseBean baseBean = new BaseBean();
                baseBean.setType(ConstantUtil.BASE_TYPE_SEND_VERIFY_CODE);
                if (ex == null) {//验�?�?�?��?�?功
                    baseBean.setCode(200);
                    baseBean.setData(smsId);
                    CommonLogger.e("smile", "短信id：" + smsId);
                } else {
                    baseBean.setCode(0);
                    baseBean.setDesc(ex.getMessage());
                }
                iView.updateData(baseBean);
            }
        });

    }

    public void login(String phone, String code) {
        iView.showLoading("登陆中...");
        User user = new User();
        //设置手机�?��?（必填）
        user.setMobilePhoneNumber(phone);


        //        以下信�?��?�针对注册用户而言，已�?注册过得用户，数�?��?会覆盖
        //                                默认注册为男性
        user.setSex(true);
        //                                 设备类型
        user.setDeviceType("android");
        //                                与设备ID绑定
        CustomInstallation customInstallation = new CustomInstallation();
        user.setInstallId(customInstallation.getInstallationId());
        user.setNick(RandomData.getRandomNick());
        user.setSignature(RandomData.getRandomSignature());
        user.setAvatar(RandomData.getRandomAvatar());
        LogUtil.e("用户的头�?信�?�:" + user.getAvatar());
        //        user.setPassword("");
        user.setTitleWallPaper(RandomData.getRandomTitleWallPaper());
        user.setSchool("中国地质大学(武汉)");
        user.setName(RandomData.getRandomName());
        user.setSex(RandomData.getRandomSex());
        user.setCollege(RandomData.getRandomCollege());
        user.setYear(RandomData.getRandomYear());
        user.setEducation(RandomData.getRandomEducation());
        user.setClassNumber(RandomData.getRandomClassNumber());
        user.setMajor(RandomData.getRandomMajor());
        user.setWallPaper(RandomData.getRandomWallPaper());
        addSubscription(user.signOrLogin(code, new SaveListener<User>() {

            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    ToastUtils.showShortToast("登录�?功");
                    LogUtil.e("登录�?功");
                    BaseApplication.getAppComponent()
                            .getSharedPreferences()
                            .edit().putBoolean(ConstantUtil.LOGIN_STATUS, true)
                            .apply();
                    //                                        登录�?功之�?�，
                    //                                        检查其他设备绑定的用户，强迫其下线
                    LogUtil.e("检查该用户绑定的其他设备.....");
                    addSubscription(UserManager.getInstance().checkInstallation(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            UserManager.getInstance().updateUserInfo(ConstantUtil.INSTALL_ID, new CustomInstallation().getInstallationId()
                                    , null);
                            if (e == null) {
                                iView.showLoading("正在获�?�好�?�资料.........");
                                updateUserInfo();
                            } else {
                                ToastUtils.showShortToast("登录失败,请�?新登录" + e.toString());
                                CommonLogger.e("登录失败" + e.toString());
                                failLogin(e);
                            }
                        }
                    }));
                } else {
                    ToastUtils.showShortToast("登录失败" + e.toString());
                    CommonLogger.e("登陆失败:" + e.toString());
                    failLogin(e);
                }
            }
        }));
    }

    private void failLogin(BmobException e) {
        BaseBean baseBean = new BaseBean();
        baseBean.setType(ConstantUtil.BASE_TYPE_PHONE_LOGIN);
        baseBean.setCode(0);
        baseBean.setDesc(e.getMessage());
        iView.updateData(baseBean);
        iView.hideLoading();
    }

    /**
     * 更新用户资料
     */
    private void updateUserInfo() {
        addSubscription(UserManager.getInstance().queryAndSaveCurrentContactsList(new FindListener<User>() {
                                                                                      @Override
                                                                                      public void done(final List<User> contacts, BmobException e) {
                                                                                          if (e == null) {
                                                                                              String uid = UserManager.getInstance().getCurrentUserObjectId();
                                                                                              MsgManager.getInstance().queryGroupTableMessage(uid, new FindListener<GroupTableMessage>() {
                                                                                                          @Override
                                                                                                          public void done(List<GroupTableMessage> list, BmobException e) {
                                                                                                              if (e == null) {
                                                                                                                  if (list != null && list.size() > 0) {
                                                                                                                      CommonLogger.e("在�?务器上查询到该用户所有的群,数目:" + list.size());
                                                                                                                      List<GroupTableMessage> newData = new ArrayList<>();
                                                                                                                      for (GroupTableMessage message :
                                                                                                                              list) {
                                                                                                                          //                                                                                                                                  这里进行判断出现是因为有�?�能建群失败的时候，未能把groupId上传上去
                                                                                                                          if (message.getGroupId() != null) {
                                                                                                                              newData.add(message);
                                                                                                                          }
                                                                                                                      }
                                                                                                                      UserDBManager.getInstance().addOrUpdateGroupTable(newData);
                                                                                                                  } else {
                                                                                                                      LogUtil.e("�?务器上没有查到该用户所拥有的群");
                                                                                                                  }
                                                                                                              } else {
                                                                                                                  LogUtil.e("在�?务器上查询所有群结构消�?�失败" + e.toString());
                                                                                                                  if (e.getErrorCode() == 101) {
                                                                                                                      CommonLogger.e("没有创立群表");
                                                                                                                  }
                                                                                                              }
                                                                                                              jumpToHome();
                                                                                                          }
                                                                                                      }

                                                                                              );
                                                                                          } else {
                                                                                              CommonLogger.e("查询好�?�失败" + e.toString());
                                                                                              jumpToHome();
                                                                                          }
                                                                                      }

                                                                                  }

        ));
    }


    private void jumpToHome() {
        iView.hideLoading();
        BaseBean baseBean = new BaseBean();
        baseBean.setType(ConstantUtil.BASE_TYPE_PHONE_LOGIN);
        baseBean.setCode(200);
        iView.updateData(baseBean);
    }
}
