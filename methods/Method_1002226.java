@Override public WebResourceResponse intercept(String url){
  if (Helpers.endsWith(url,FIRST_SCRIPT_NAME)) {
    Log.d(TAG,"Begin onInitScripts");
    InputStream onInitScripts=mManager.getOnInitScripts();
    Log.d(TAG,"End onInitScripts");
    return prependResponse(url,onInitScripts);
  }
  if (Helpers.endsWith(url,LAST_SCRIPT_NAME)) {
    Log.d(TAG,"Begin onLoadScript");
    InputStream onLoadScripts=mManager.getOnLoadScripts();
    Log.d(TAG,"End onLoadScript");
    return appendResponse(url,onLoadScripts);
  }
  if (Helpers.endsWith(url,LAST_STYLE_NAME)) {
    Log.d(TAG,"Begin onStyles");
    InputStream styles=mManager.getStyles();
    Log.d(TAG,"End onStyles");
    return appendResponse(url,styles);
  }
  return null;
}
