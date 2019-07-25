@SuppressLint("SetJavaScriptEnabled") private void initialize(){
  try {
    loadUrl("file:///android_asset/html/markdown.html");
    getSettings().setJavaScriptEnabled(true);
    addJavascriptInterface(mMarkDownWebAppInterface,"Android");
    getSettings().setAllowUniversalAccessFromFileURLs(true);
    mIsInitialised=true;
  }
 catch (  Exception e) {
    Log.e(LOG_TAG,"## initialize() failed " + e.getMessage(),e);
  }
}
