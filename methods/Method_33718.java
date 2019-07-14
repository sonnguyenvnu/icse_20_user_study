private static void showDialog(View v,String[] items,OnLoginListener listener,boolean isLogin){
  AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
  builder.setTitle("????");
  builder.setItems(items,(dialog,which) -> {
switch (which) {
case 0:
      listener.loginGitHub();
    break;
case 1:
  if (isLogin) {
    new LoginModel().logout(() -> {
      Injection.get().deleteAllData();
      UserUtil.handleLoginFailure();
      ToastUtil.showToastLong("????");
    }
);
  }
 else {
    listener.loginWanAndroid();
  }
break;
default :
break;
}
}
);
builder.show();
}
