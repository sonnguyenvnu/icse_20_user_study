/** 
 * Removes a breezemoon.
 * @param context the specified context
 */
@RequestProcessing(value="/breezemoon/{id}",method=HttpMethod.DELETE) @Before({StopwatchStartAdvice.class,LoginCheck.class,CSRFCheck.class,PermissionCheck.class}) @After(StopwatchEndAdvice.class) public void removeBreezemoon(final RequestContext context){
  final String id=context.pathVar("id");
  context.renderJSON();
  try {
    final JSONObject breezemoon=breezemoonQueryService.getBreezemoon(id);
    if (null == breezemoon) {
      throw new ServiceException(langPropsService.get("queryFailedLabel"));
    }
    final JSONObject user=Sessions.getUser();
    if (!breezemoon.optString(Breezemoon.BREEZEMOON_AUTHOR_ID).equals(user.optString(Keys.OBJECT_ID))) {
      throw new ServiceException(langPropsService.get("sc403Label"));
    }
    breezemoonMgmtService.removeBreezemoon(id);
    context.renderJSONValue(Keys.STATUS_CODE,StatusCodes.SUCC);
  }
 catch (  final Exception e) {
    context.renderMsg(e.getMessage());
    context.renderJSONValue(Keys.STATUS_CODE,StatusCodes.ERR);
  }
}
