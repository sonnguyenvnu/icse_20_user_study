private void runJsCode(String code){
  if (Build.VERSION.SDK_INT >= 21) {
    webView.evaluateJavascript(code,null);
  }
 else {
    try {
      webView.loadUrl("javascript:" + code);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
