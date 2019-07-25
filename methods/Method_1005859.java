public void login(LoginDto loginDto,User user) throws MyException {
  String token=Aes.encrypt(user.getId());
  MyCookie.addCookie(IConst.COOKIE_TOKEN,token);
  userCache.add(user.getId(),new LoginInfoDto(user));
  MyCookie.addCookie(IConst.C_COOKIE_USERID,user.getId());
  MyCookie.addCookie(IConst.COOKIE_USERNAME,loginDto.getUserName());
  MyCookie.addCookie(IConst.COOKIE_REMBER_PWD,loginDto.getRemberPwd());
  if (loginDto.getRemberPwd() == null || loginDto.getRemberPwd().equalsIgnoreCase("yes")) {
    MyCookie.addCookie(IConst.COOKIE_PASSWORD,loginDto.getPassword(),true);
  }
 else {
    MyCookie.deleteCookie(IConst.COOKIE_PASSWORD);
  }
  loginDto.setSessionAdminName(loginDto.getUserName());
}
