@Override public UserInfo grant(TokenParameter tokenParameter){
  String tenantCode=tokenParameter.getArgs().getStr("tenantCode");
  String account=tokenParameter.getArgs().getStr("account");
  String password=tokenParameter.getArgs().getStr("password");
  UserInfo userInfo=null;
  if (Func.isNoneBlank(account,password)) {
    String userType=tokenParameter.getArgs().getStr("userType");
    R<UserInfo> result;
    if (userType.equals(BladeUserEnum.WEB.getName())) {
      result=userClient.userInfo(tenantCode,account,DigestUtil.encrypt(password));
    }
 else     if (userType.equals(BladeUserEnum.APP.getName())) {
      result=userClient.userInfo(tenantCode,account,DigestUtil.encrypt(password));
    }
 else {
      result=userClient.userInfo(tenantCode,account,DigestUtil.encrypt(password));
    }
    userInfo=result.isSuccess() ? result.getData() : null;
  }
  return userInfo;
}
