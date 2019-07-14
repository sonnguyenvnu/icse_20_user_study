private String makeSignature(Request request,String timestamp){
  String key=ApiCredential.Frodo.SECRET;
  if (TextUtils.isEmpty(key)) {
    return null;
  }
  StringBuilder builder=new StringBuilder();
  builder.append(request.method());
  String path=request.url().encodedPath();
  path=Uri.decode(path);
  if (path.endsWith("/")) {
    path=path.substring(0,path.length() - 1);
  }
  path=Uri.encode(path);
  builder.append("&").append(path);
  String authorization=request.header(Http.Headers.AUTHORIZATION);
  if (!TextUtils.isEmpty(authorization)) {
    String authToken=authorization.substring(7);
    if (!TextUtils.isEmpty(authToken)) {
      builder.append("&").append(authToken);
    }
  }
  builder.append("&").append(timestamp);
  String signature=builder.toString();
  signature=hmacSha1(key,signature);
  return signature;
}
