/** 
 * ???????
 */
public static void getLoginStatus(){
  Injection.get().getSingleBean(new UserDataCallback(){
    @Override public void onDataNotAvailable(){
      SPUtils.putBoolean(Constants.IS_LOGIN,false);
    }
    @Override public void getData(    User bean){
      SPUtils.putBoolean(Constants.IS_LOGIN,true);
    }
  }
);
}
