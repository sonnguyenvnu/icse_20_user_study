/** 
 * Gets unread count of notifications.
 * @param context the specified context
 */
@RequestProcessing(value="/notifications/unread/count",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After({StopwatchEndAdvice.class}) public void getUnreadNotificationCount(final RequestContext context){
  final JSONObject currentUser=Sessions.getUser();
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  final Map<String,Object> dataModel=new HashMap<>();
  fillNotificationCount(userId,dataModel);
  context.renderJSON(new JSONObject(dataModel)).renderTrueResult().renderJSONValue(UserExt.USER_NOTIFY_STATUS,currentUser.optInt(UserExt.USER_NOTIFY_STATUS));
}
