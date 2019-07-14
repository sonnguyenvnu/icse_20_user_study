public MiaoshaUser getByToken(HttpServletResponse response,String token){
  if (StringUtils.isEmpty(token)) {
    return null;
  }
  MiaoshaUser user=redisService.get(MiaoShaUserKey.token,token,MiaoshaUser.class);
  if (user != null) {
    addCookie(response,token,user);
  }
  return user;
}
