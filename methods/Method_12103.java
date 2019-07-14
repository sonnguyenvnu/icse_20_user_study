public boolean register(String userName,String passWord,HttpServletResponse response,HttpServletRequest request){
  MiaoshaUser miaoShaUser=new MiaoshaUser();
  miaoShaUser.setNickname(userName);
  String salt=MD5Utils.getSaltT();
  String DBPassWord=MD5Utils.formPassToDBPass(passWord,salt);
  miaoShaUser.setPassword(DBPassWord);
  miaoShaUser.setRegisterDate(new Date());
  miaoShaUser.setSalt(salt);
  miaoShaUser.setNickname(userName);
  try {
    miaoShaUserMapper.insertMiaoShaUser(miaoShaUser);
    IpLog log=new IpLog(userName,new Date(),request.getRemoteAddr(),USERTYPE_NORMAL,null);
    MiaoshaUser user=miaoShaUserMapper.getByNickname(miaoShaUser.getNickname());
    if (user == null) {
      return false;
    }
    String token=UUIDUtil.uuid();
    addCookie(response,token,user);
  }
 catch (  Exception e) {
    logger.error("????",e);
    return false;
  }
  return true;
}
