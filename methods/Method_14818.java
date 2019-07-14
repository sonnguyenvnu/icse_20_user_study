@Override public void onDialogButtonClick(int requestCode,boolean isPositive){
  if (!isPositive) {
    return;
  }
switch (requestCode) {
case 0:
    HttpRequest.logout(HTTP_LOUOUT,this);
  APIJSONApplication.getInstance().logout();
toActivity(MainTabActivity.createIntent(context).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
context.finish();
break;
default :
break;
}
}
