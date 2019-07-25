public void login(String phone,String code){
  iView.showLoading("???...");
  User user=new User();
  user.setMobilePhoneNumber(phone);
  user.setSex(true);
  user.setDeviceType("android");
  CustomInstallation customInstallation=new CustomInstallation();
  user.setInstallId(customInstallation.getInstallationId());
  user.setNick(RandomData.getRandomNick());
  user.setSignature(RandomData.getRandomSignature());
  user.setAvatar(RandomData.getRandomAvatar());
  LogUtil.e("???????:" + user.getAvatar());
  user.setTitleWallPaper(RandomData.getRandomTitleWallPaper());
  user.setSchool("??????(??)");
  user.setName(RandomData.getRandomName());
  user.setSex(RandomData.getRandomSex());
  user.setCollege(RandomData.getRandomCollege());
  user.setYear(RandomData.getRandomYear());
  user.setEducation(RandomData.getRandomEducation());
  user.setClassNumber(RandomData.getRandomClassNumber());
  user.setMajor(RandomData.getRandomMajor());
  user.setWallPaper(RandomData.getRandomWallPaper());
  addSubscription(user.signOrLogin(code,new SaveListener<User>(){
    @Override public void done(    User user,    BmobException e){
      if (e == null) {
        ToastUtils.showShortToast("????");
        LogUtil.e("????");
        BaseApplication.getAppComponent().getSharedPreferences().edit().putBoolean(ConstantUtil.LOGIN_STATUS,true).apply();
        LogUtil.e("????????????.....");
        addSubscription(UserManager.getInstance().checkInstallation(new UpdateListener(){
          @Override public void done(          BmobException e){
            UserManager.getInstance().updateUserInfo(ConstantUtil.INSTALL_ID,new CustomInstallation().getInstallationId(),null);
            if (e == null) {
              iView.showLoading("????????.........");
              updateUserInfo();
            }
 else {
              ToastUtils.showShortToast("????,?????" + e.toString());
              CommonLogger.e("????" + e.toString());
              failLogin(e);
            }
          }
        }
));
      }
 else {
        ToastUtils.showShortToast("????" + e.toString());
        CommonLogger.e("????:" + e.toString());
        failLogin(e);
      }
    }
  }
));
}
