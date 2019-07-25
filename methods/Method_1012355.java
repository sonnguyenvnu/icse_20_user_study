/** 
 * ????
 */
public void logout(){
  List<String> list=UserDBManager.getInstance().getAllFriendId();
  list.add(uid);
  SharedPreferences.Editor edit=BaseApplication.getAppComponent().getSharedPreferences().edit();
  for (  String item : list) {
    edit.putString(ConstantUtil.UPDATE_TIME_SHARE + item,null);
  }
  edit.putBoolean(ConstantUtil.LOGIN_STATUS,false);
  edit.apply();
  uid=null;
  User.logOut();
  MobclickAgent.onProfileSignOff();
}
