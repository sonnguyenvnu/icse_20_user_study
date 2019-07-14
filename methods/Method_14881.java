@Override public void onBottomMenuItemClick(int intentCode){
  if (user == null) {
    Log.e(TAG,"onBottomMenuItemClick  user == null >> return;");
    return;
  }
  if (verifyLogin() == false) {
    return;
  }
switch (intentCode) {
case MenuUtil.INTENT_CODE_EDIT:
    toActivity(UserActivity.createIntent(context,id).putExtra(UserActivity.INTENT_IS_ON_EDIT_MODE,true));
  break;
case MenuUtil.INTENT_CODE_ADD:
HttpRequest.setIsFriend(id,true,HTTP_ADD,this);
break;
case MenuUtil.INTENT_CODE_DELETE:
HttpRequest.setIsFriend(id,false,HTTP_DELETE,this);
break;
case MenuUtil.INTENT_CODE_QRCODE:
toActivity(QRCodeActivity.createIntent(context,id));
break;
case MenuUtil.INTENT_CODE_SEND:
CommonUtil.shareInfo(context,JSON.toJSONString(user));
break;
default :
break;
}
}
