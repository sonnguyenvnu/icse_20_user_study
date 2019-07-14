public static void handleLoginFailure(){
  SPUtils.putBoolean(Constants.IS_LOGIN,false);
  SPUtils.putString("cookie","");
  SPUtils.remove("cookie");
}
