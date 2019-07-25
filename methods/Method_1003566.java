@Override public boolean intercept(@NonNull HttpRequest request){
  String httpPath=request.getPath();
  File source=findPathResource(httpPath);
  return source != null;
}
