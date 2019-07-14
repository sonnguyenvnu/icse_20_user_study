/** 
 * ????????? Scheme: https host: www.jianshu.com path: /p/1cbaf784c29c url = scheme + "://" + host + path;
 */
private void getDataFromBrowser(Intent intent){
  Uri data=intent.getData();
  if (data != null) {
    try {
      String scheme=data.getScheme();
      String host=data.getHost();
      String path=data.getPath();
      String url=scheme + "://" + host + path;
      webView.loadUrl(url);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
}
