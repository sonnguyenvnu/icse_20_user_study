public void setWikiContent(@NonNull String source,@Nullable String baseUrl){
  addJavascriptInterface(new MarkDownInterceptorInterface(this,true),"Android");
  String page=GithubHelper.generateContent(getContext(),source,baseUrl,AppHelper.isNightMode(getResources()),AppHelper.isNightMode(getResources()),true);
  post(() -> loadDataWithBaseURL("file:///android_asset/md/",page,"text/html","utf-8",null));
}
