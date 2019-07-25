@Override public void run(AccountManagerFuture<Bundle> value){
  try {
    String result=value.getResult().getString(AccountManager.KEY_AUTHTOKEN);
    if (result == null) {
      loginFailed();
    }
 else {
      mWebView.loadUrl(result);
      mTab.setDeviceAccountLogin(null);
      if (mTab.inForeground()) {
        mWebViewController.hideAutoLogin(mTab);
      }
    }
  }
 catch (  Exception e) {
    loginFailed();
  }
}
