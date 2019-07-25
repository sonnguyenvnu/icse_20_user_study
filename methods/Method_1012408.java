@Override public UserInfo grant(TokenParameter tokenParameter){
  String grantType=tokenParameter.getArgs().getStr("grantType");
  String refreshToken=tokenParameter.getArgs().getStr("refreshToken");
  UserInfo userInfo=null;
  if (Func.isNoneBlank(grantType,refreshToken) && grantType.equals(TokenConstant.REFRESH_TOKEN)) {
    Claims claims=SecureUtil.parseJWT(refreshToken);
    String tokenType=Func.toStr(Objects.requireNonNull(claims).get(TokenConstant.TOKEN_TYPE));
    if (tokenType.equals(TokenConstant.REFRESH_TOKEN)) {
      R<UserInfo> result=userClient.userInfo(Func.toLong(claims.get(TokenConstant.USER_ID)));
      userInfo=result.isSuccess() ? result.getData() : null;
    }
  }
  return userInfo;
}
