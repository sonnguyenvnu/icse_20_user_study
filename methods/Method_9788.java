/** 
 * Adds a breezemoon. <p> The request json object (breezemoon): <pre> { "breezemoonContent": "" } </pre> </p>
 * @param context the specified context
 */
@RequestProcessing(value="/breezemoon",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,LoginCheck.class,CSRFCheck.class,PermissionCheck.class}) @After(StopwatchEndAdvice.class) public void addBreezemoon(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  if (isInvalid(context,requestJSONObject)) {
    return;
  }
  final JSONObject breezemoon=new JSONObject();
  final String breezemoonContent=requestJSONObject.optString(Breezemoon.BREEZEMOON_CONTENT);
  breezemoon.put(Breezemoon.BREEZEMOON_CONTENT,breezemoonContent);
  final JSONObject user=Sessions.getUser();
  final String authorId=user.optString(Keys.OBJECT_ID);
  breezemoon.put(Breezemoon.BREEZEMOON_AUTHOR_ID,authorId);
  final String ip=Requests.getRemoteAddr(request);
  breezemoon.put(Breezemoon.BREEZEMOON_IP,ip);
  final String ua=Headers.getHeader(request,Common.USER_AGENT,"");
  breezemoon.put(Breezemoon.BREEZEMOON_UA,ua);
  final JSONObject address=Geos.getAddress(ip);
  if (null != address) {
    breezemoon.put(Breezemoon.BREEZEMOON_CITY,address.optString(Common.CITY));
  }
  try {
    breezemoonMgmtService.addBreezemoon(breezemoon);
    context.renderJSONValue(Keys.STATUS_CODE,StatusCodes.SUCC);
  }
 catch (  final Exception e) {
    context.renderMsg(e.getMessage());
    context.renderJSONValue(Keys.STATUS_CODE,StatusCodes.ERR);
  }
}
