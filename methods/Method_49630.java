private Map<String,String> getCredentialsMap(final ChannelHandlerContext ctx,final FullHttpRequest req){
  final String authorizationHeader=req.headers().get("Authorization");
  if (authorizationHeader == null) {
    return null;
  }
  if (!(authorizationHeader.startsWith(basic) || authorizationHeader.startsWith(token))) {
    return null;
  }
  final String authType=authorizationHeader.startsWith(basic) ? basic : token;
  return getAuthCredMap(authorizationHeader,authType);
}
