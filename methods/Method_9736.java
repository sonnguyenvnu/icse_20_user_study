/** 
 * Submits character.
 * @param context the specified context
 */
@RequestProcessing(value="/activity/character/submit",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After({StopwatchEndAdvice.class}) public void submitCharacter(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  context.renderJSON().renderFalseResult();
  JSONObject requestJSONObject;
  try {
    requestJSONObject=context.requestJSON();
    request.setAttribute(Keys.REQUEST,requestJSONObject);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Submits character failed",e);
    context.renderJSON(false).renderMsg(langPropsService.get("activityCharacterRecognizeFailedLabel"));
    return;
  }
  final JSONObject currentUser=Sessions.getUser();
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  final String dataURL=requestJSONObject.optString("dataURL");
  final String dataPart=StringUtils.substringAfter(dataURL,",");
  final String character=requestJSONObject.optString("character");
  final JSONObject result=activityMgmtService.submitCharacter(userId,dataPart,character);
  context.renderJSON(result);
}
