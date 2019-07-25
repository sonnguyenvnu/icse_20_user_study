@Override public void intercept(Invocation inv){
  if (!JwtManager.me().getConfig().isConfigOk()) {
    inv.invoke();
    return;
  }
  HttpServletRequest request=inv.getController().getRequest();
  String token=request.getHeader(JwtManager.me().getHttpHeaderName());
  if (StrUtil.isBlank(token)) {
    processInvoke(inv,null);
    return;
  }
  Map map=JwtManager.me().parseJwtToken(token);
  if (map == null) {
    processInvoke(inv,null);
    return;
  }
  try {
    JwtManager.me().holdJwts(map);
    processInvoke(inv,map);
  }
  finally {
    JwtManager.me().releaseJwts();
  }
}
