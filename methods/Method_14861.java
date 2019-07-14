@Override public void onHttpResponse(int requestCode,String resultJson,Exception e){
  final JSONResponse response=new JSONResponse(resultJson);
  final JSONResponse response2;
  dismissProgressDialog();
switch (requestCode) {
case HTTP_CHECK_REGISTER:
    response2=response.getJSONResponse(HttpRequest.PRIVACY_);
  Log.i(TAG,"checkPassword result = " + resultJson);
runUiThread(new Runnable(){
  @Override public void run(){
    showVerifyGet(false);
    if (JSONResponse.isSuccess(response2) == false) {
      showShortToast(R.string.get_failed);
    }
 else     if (JSONResponse.isExist(response2)) {
      if (type == TYPE_REGISTER) {
        showShortToast("???????");
      }
 else {
        getVerifyFromServer(type == TYPE_VERIFY ? Verify.TYPE_LOGIN : Verify.TYPE_PASSWORD);
      }
    }
 else {
      if (type == TYPE_REGISTER) {
        getVerifyFromServer(Verify.TYPE_REGISTER);
      }
 else {
        showShortToast("??????");
      }
    }
  }
}
);
break;
case HTTP_CHECK_VERIFY:
response2=response.getJSONResponse(Verify.class.getSimpleName());
runUiThread(new Runnable(){
@Override public void run(){
if (JSONResponse.isExist(response2)) {
switch (type) {
case TYPE_REGISTER:
  register();
break;
case TYPE_RESET:
setPassword();
break;
default :
saveAndExit(newResult());
break;
}
}
 else {
EditTextUtil.showInputedError(context,etPasswordVerify,response.getCode() == CODE_TIME_OUT ? "??????" : "?????");
}
}
}
);
break;
case HTTP_GET_VERIFY:
final Verify verify=response.getObject(Verify.class);
runUiThread(new Runnable(){
@Override public void run(){
showVerifyGet(false);
if (verify == null || StringUtil.isNotEmpty(verify.getVerify(),true) == false) {
showShortToast(R.string.get_failed);
}
 else {
Log.i(TAG,"????");
time.start();
Toast.makeText(context,"????\n" + verify.getVerify(),Toast.LENGTH_LONG).show();
}
}
}
);
break;
case HTTP_REGISTER:
User user=response.getObject(User.class);
dismissProgressDialog();
if (user == null || user.getId() <= 0 || JSONResponse.isSuccess(response.getJSONResponse(User.class.getSimpleName())) == false) {
if (response.getCode() == CODE_TIME_OUT || response.getCode() == 412) {
EditTextUtil.showInputedError(context,etPasswordVerify,response.getCode() == CODE_TIME_OUT ? "??????" : "?????");
}
 else {
showShortToast("?????????????");
}
}
 else {
showShortToast("????");
APIJSONApplication.getInstance().saveCurrentUser(user);
saveAndExit(newResult().putExtra(INTENT_ID,user.getId()));
}
break;
case HTTP_RESET_PASSWORD:
response2=response.getJSONResponse(Privacy.class.getSimpleName());
dismissProgressDialog();
if (JSONResponse.isSuccess(response2) == false) {
EditTextUtil.showInputedError(context,etPasswordVerify,response.getCode() == CODE_TIME_OUT ? "??????" : "?????");
}
 else {
showShortToast("????????????");
saveAndExit(newResult().putExtra(INTENT_PHONE,phone));
}
break;
default :
break;
}
}
