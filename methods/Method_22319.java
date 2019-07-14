@SuppressWarnings("WeakerAccess") protected void postMultipart(@NonNull CoreConfiguration configuration,@NonNull Context context,@NonNull String contentType,@Nullable String login,@Nullable String password,int connectionTimeOut,int socketTimeOut,@Nullable Map<String,String> headers,@NonNull String content,@NonNull URL url,@NonNull List<Uri> attachments) throws IOException {
  new MultipartHttpRequest(configuration,context,contentType,login,password,connectionTimeOut,socketTimeOut,headers).send(url,Pair.create(content,attachments));
}
