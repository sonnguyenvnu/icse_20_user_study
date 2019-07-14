@SuppressWarnings("WeakerAccess") protected void sendHttpRequests(@NonNull CoreConfiguration configuration,@NonNull Context context,@NonNull Method method,@NonNull String contentType,@Nullable String login,@Nullable String password,int connectionTimeOut,int socketTimeOut,@Nullable Map<String,String> headers,@NonNull String content,@NonNull URL url,@NonNull List<Uri> attachments) throws IOException {
switch (method) {
case POST:
    if (attachments.isEmpty()) {
      sendWithoutAttachments(configuration,context,method,contentType,login,password,connectionTimeOut,socketTimeOut,headers,content,url);
    }
 else {
      postMultipart(configuration,context,contentType,login,password,connectionTimeOut,socketTimeOut,headers,content,url,attachments);
    }
  break;
case PUT:
sendWithoutAttachments(configuration,context,method,contentType,login,password,connectionTimeOut,socketTimeOut,headers,content,url);
for (Uri uri : attachments) {
putAttachment(configuration,context,login,password,connectionTimeOut,socketTimeOut,headers,url,uri);
}
break;
}
}
