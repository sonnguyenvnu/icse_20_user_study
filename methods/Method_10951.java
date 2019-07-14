public static void loadData(WebView webView,String content){
  webView.loadDataWithBaseURL(null,content,"text/html","UTF-8",null);
}
