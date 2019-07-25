@Override public boolean intercept(@NonNull HttpRequest request){
  tryScanFile();
  String httpPath=request.getPath();
  return mPatternMap.containsKey(httpPath);
}
