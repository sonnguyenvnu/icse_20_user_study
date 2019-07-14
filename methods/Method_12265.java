public boolean login(HttpServletResponse response,LoginVo loginVo){
  if (loginVo == null) {
    throw new GlobleException(SYSTEM_ERROR);
  }
  String mobile=loginVo.getMobile();
  String password=loginVo.getPassword();
  MiaoshaUser user=getByNickName(mobile);
  if (user == null) {
    throw new GlobleException(MOBILE_NOT_EXIST);
  }
  String dbPass=user.getPassword();
  String saltDb=user.getSalt();
  String calcPass=MD5Utils.formPassToDBPass(password,saltDb);
  if (!calcPass.equals(dbPass)) {
    throw new GlobleException(PASSWORD_ERROR);
  }
  String token=UUIDUtil.uuid();
  addCookie(response,token,user);
  return true;
}
