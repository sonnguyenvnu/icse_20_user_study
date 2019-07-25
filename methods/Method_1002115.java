private void init(){
  setTitle(R.string.just_a_sec);
  setBackButtonText(null);
  setNextButtonText(null);
  View loading=getLayoutInflater().inflate(R.layout.login_assistant_loading,authContent,false);
  authContent.removeAllViews();
  authContent.addView(loading);
  setMessage(R.string.auth_connecting);
  CookieManager.getInstance().setAcceptCookie(true);
  if (SDK_INT >= LOLLIPOP) {
    CookieManager.getInstance().removeAllCookies(value -> start());
  }
 else {
    CookieManager.getInstance().removeAllCookie();
    start();
  }
}
