@SuppressWarnings("WeakerAccess") protected void sendWithoutAttachments(@NonNull CoreConfiguration configuration,@NonNull Context context,@NonNull Method method,@NonNull String contentType,@Nullable String login,@Nullable String password,int connectionTimeOut,int socketTimeOut,@Nullable Map<String,String> headers,@NonNull String content,@NonNull URL url) throws IOException {
  new DefaultHttpRequest(configuration,context,method,contentType,login,password,connectionTimeOut,socketTimeOut,headers).send(url,content);
}
