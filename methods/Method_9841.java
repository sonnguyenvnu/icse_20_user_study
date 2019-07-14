/** 
 * Updates user geo status.
 * @param context the specified context
 */
@RequestProcessing(value="/settings/geo/status",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class}) public void updateGeoStatus(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  JSONObject requestJSONObject;
  try {
    requestJSONObject=context.requestJSON();
    request.setAttribute(Keys.REQUEST,requestJSONObject);
  }
 catch (  final Exception e) {
    LOGGER.warn(e.getMessage());
    requestJSONObject=new JSONObject();
  }
  int geoStatus=requestJSONObject.optInt(UserExt.USER_GEO_STATUS);
  if (UserExt.USER_GEO_STATUS_C_PRIVATE != geoStatus && UserExt.USER_GEO_STATUS_C_PUBLIC != geoStatus) {
    geoStatus=UserExt.USER_GEO_STATUS_C_PUBLIC;
  }
  try {
    JSONObject user=Sessions.getUser();
    final String userId=user.optString(Keys.OBJECT_ID);
    user=userQueryService.getUser(userId);
    user.put(UserExt.USER_GEO_STATUS,geoStatus);
    userMgmtService.updateUser(user.optString(Keys.OBJECT_ID),user);
    context.renderTrueResult();
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
