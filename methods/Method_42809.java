@RequestMapping(value="/savePayPass",method={RequestMethod.POST,RequestMethod.GET}) @ResponseBody public ApiCommonResultVo savePayPass(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
  RpUserInfo rpUserInfo=(RpUserInfo)request.getSession().getAttribute(ConstantClass.USER);
  String oldPayPass=request.getParameter("oldPayPass");
  String newPayPass=request.getParameter("newPayPass");
  if (!EncryptUtil.encodeMD5String(oldPayPass).equals(rpUserInfo.getPayPwd())) {
    return new ApiCommonResultVo(-1,"????????????","");
  }
 else {
    rpUserInfo.setPayPwd(EncryptUtil.encodeMD5String(newPayPass));
    rpUserInfoService.updateData(rpUserInfo);
    request.getSession().setAttribute(ConstantClass.USER,rpUserInfo);
    return new ApiCommonResultVo(0,"????","");
  }
}
