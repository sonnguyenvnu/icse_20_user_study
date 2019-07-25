@Override public void intercept(Chain chain) throws Exception {
  Uri uri=chain.request().uri;
  String currentHost=uri.getHost();
  String currentPath=uri.getPath();
  long currentTime=System.currentTimeMillis();
  if (currentHost.equals(preHost) && currentPath.equals(prePath) && (currentTime - preTargetTime) < 1000) {
    chain.callback().onError(new NavigationFailException("target '" + uri.toString() + "' can't launch twice in a second"));
  }
 else {
    preHost=currentHost;
    prePath=currentPath;
    preTargetTime=currentTime;
    chain.proceed(chain.request());
  }
}
