public void setGithubContent(@NonNull String source,@Nullable String baseUrl,boolean toggleNestScrolling,boolean enableBridge){
  if (enableBridge)   addJavascriptInterface(new MarkDownInterceptorInterface(this,toggleNestScrolling),"Android");
  String page=GithubHelper.generateContent(getContext(),source,baseUrl,AppHelper.isNightMode(getResources()),AppHelper.isNightMode(getResources()),false);
  post(() -> loadDataWithBaseURL("file:///android_asset/md/",page,"text/html","utf-8",null));
}
