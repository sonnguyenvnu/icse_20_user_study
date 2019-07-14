private void loadCode(String page){
  post(() -> loadDataWithBaseURL("file:///android_asset/highlight/",page,"text/html","utf-8",null));
}
