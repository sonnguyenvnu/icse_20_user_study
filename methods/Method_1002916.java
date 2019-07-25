/** 
 * Checks whether the request has right permission to access the service. In this example, a  {@code Cookie}header is used to hold the information. If a  {@code username} key exists in the {@code Cookie} header,the request is treated as authenticated.
 */
@Override public CompletionStage<Boolean> authorize(ServiceRequestContext ctx,HttpRequest data){
  final String cookie=data.headers().get(HttpHeaderNames.COOKIE);
  if (cookie == null) {
    return CompletableFuture.completedFuture(false);
  }
  final boolean authenticated=ServerCookieDecoder.LAX.decode(cookie).stream().anyMatch(c -> "username".equals(c.name()) && !Strings.isNullOrEmpty(c.value()));
  return CompletableFuture.completedFuture(authenticated);
}
