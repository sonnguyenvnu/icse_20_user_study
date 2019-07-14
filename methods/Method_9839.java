/** 
 * Updates user i18n.
 * @param context the specified context
 */
@RequestProcessing(value="/settings/i18n",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class}) public void updateI18n(final RequestContext context){
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
  String userLanguage=requestJSONObject.optString(UserExt.USER_LANGUAGE,Locale.SIMPLIFIED_CHINESE.toString());
  if (!Languages.getAvailableLanguages().contains(userLanguage)) {
    userLanguage=Locale.US.toString();
  }
  String userTimezone=requestJSONObject.optString(UserExt.USER_TIMEZONE,TimeZone.getDefault().getID());
  if (!Arrays.asList(TimeZone.getAvailableIDs()).contains(userTimezone)) {
    userTimezone=TimeZone.getDefault().getID();
  }
  try {
    JSONObject user=Sessions.getUser();
    final String userId=user.optString(Keys.OBJECT_ID);
    user=userQueryService.getUser(userId);
    user.put(UserExt.USER_LANGUAGE,userLanguage);
    user.put(UserExt.USER_TIMEZONE,userTimezone);
    userMgmtService.updateUser(user.optString(Keys.OBJECT_ID),user);
    context.renderTrueResult();
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
