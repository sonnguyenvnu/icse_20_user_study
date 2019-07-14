/** 
 * Updates a breezemoon. <p> The request json object (breezemoon): <pre> { "breezemoonContent": "" } </pre> </p>
 * @param context the specified context
 */
@RequestProcessing(value="/breezemoon/{id}",method=HttpMethod.PUT) @Before({StopwatchStartAdvice.class,LoginCheck.class,CSRFCheck.class,PermissionCheck.class}) @After(StopwatchEndAdvice.class) public void updateBreezemoon(final RequestContext context){
  final String id=context.pathVar("id");
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  if (isInvalid(context,requestJSONObject)) {
    return;
  }
  try {
    final JSONObject old=breezemoonQueryService.getBreezemoon(id);
    if (null == old) {
      throw new ServiceException(langPropsService.get("queryFailedLabel"));
    }
    final JSONObject user=Sessions.getUser();
    if (!old.optString(Breezemoon.BREEZEMOON_AUTHOR_ID).equals(user.optString(Keys.OBJECT_ID))) {
      throw new ServiceException(langPropsService.get("sc403Label"));
    }
    final JSONObject breezemoon=new JSONObject();
    breezemoon.put(Keys.OBJECT_ID,id);
    final String breezemoonContent=requestJSONObject.optString(Breezemoon.BREEZEMOON_CONTENT);
    breezemoon.put(Breezemoon.BREEZEMOON_CONTENT,breezemoonContent);
    final String ip=Requests.getRemoteAddr(request);
    breezemoon.put(Breezemoon.BREEZEMOON_IP,ip);
    final String ua=Headers.getHeader(request,Common.USER_AGENT,"");
    breezemoon.put(Breezemoon.BREEZEMOON_UA,ua);
    breezemoonMgmtService.updateBreezemoon(breezemoon);
    context.renderJSONValue(Keys.STATUS_CODE,StatusCodes.SUCC);
  }
 catch (  final Exception e) {
    context.renderMsg(e.getMessage());
    context.renderJSONValue(Keys.STATUS_CODE,StatusCodes.ERR);
  }
}
