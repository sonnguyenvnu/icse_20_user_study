@RequestMapping(value="/savePassword",method={RequestMethod.POST,RequestMethod.GET}) @ResponseBody public ApiCommonResultVo savePassword(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
  RpUserInfo rpUserInfo=(RpUserInfo)request.getSession().getAttribute(ConstantClass.USER);
  String oldPassword=request.getParameter("oldPassword");
  String newPassword=request.getParameter("newPassword");
  if (!EncryptUtil.encodeMD5String(oldPassword).equals(rpUserInfo.getPassword())) {
    return new ApiCommonResultVo(-1,"?????????","");
  }
 else {
    rpUserInfo.setPassword(EncryptUtil.encodeMD5String(newPassword));
    rpUserInfoService.updateData(rpUserInfo);
    request.getSession().setAttribute(ConstantClass.USER,rpUserInfo);
    return new ApiCommonResultVo(0,"????","");
  }
}
