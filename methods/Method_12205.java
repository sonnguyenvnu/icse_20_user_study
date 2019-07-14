private MiaoshaUser getUser(HttpServletRequest request,HttpServletResponse response){
  String paramToken=request.getParameter(MiaoShaUserService.COOKIE_NAME_TOKEN);
  String cookieToken=getCookieValue(request,MiaoShaUserService.COOKIE_NAME_TOKEN);
  if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
    return null;
  }
  String token=StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
  return userService.getByToken(response,token);
}
