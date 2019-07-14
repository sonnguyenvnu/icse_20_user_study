@Override public void doAdvice(final RequestContext context) throws RequestProcessAdviceException {
  final HttpServletRequest request=context.getRequest();
  final JSONObject exception=new JSONObject();
  exception.put(Keys.MSG,HttpServletResponse.SC_UNAUTHORIZED + ", " + context.requestURI());
  exception.put(Keys.STATUS_CODE,HttpServletResponse.SC_UNAUTHORIZED);
  JSONObject currentUser=Sessions.getUser();
  if (null == currentUser) {
    throw new RequestProcessAdviceException(exception);
  }
  final int point=currentUser.optInt(UserExt.USER_POINT);
  final int appRole=currentUser.optInt(UserExt.USER_APP_ROLE);
  if (UserExt.USER_APP_ROLE_C_HACKER == appRole) {
    currentUser.put(UserExt.USER_T_POINT_HEX,Integer.toHexString(point));
  }
 else {
    currentUser.put(UserExt.USER_T_POINT_CC,UserExt.toCCString(point));
  }
  request.setAttribute(User.USER,currentUser);
}
