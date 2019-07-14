/** 
 * Adds a chat message. <p> The request json object (a chat message): <pre> { "content": "" } </pre> </p>
 * @param context the specified context
 */
@RequestProcessing(value="/chat-room/send",method=HttpMethod.POST) @Before({LoginCheck.class,ChatMsgAddValidation.class}) public synchronized void addChatRoomMsg(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=(JSONObject)context.attr(Keys.REQUEST);
  String content=requestJSONObject.optString(Common.CONTENT);
  content=shortLinkQueryService.linkArticle(content);
  content=Emotions.convert(content);
  content=Markdowns.toHTML(content);
  content=Markdowns.clean(content,"");
  final JSONObject currentUser=Sessions.getUser();
  final String userName=currentUser.optString(User.USER_NAME);
  final JSONObject msg=new JSONObject();
  msg.put(User.USER_NAME,userName);
  msg.put(UserExt.USER_AVATAR_URL,currentUser.optString(UserExt.USER_AVATAR_URL));
  msg.put(Common.CONTENT,content);
  msg.put(Common.TIME,System.currentTimeMillis());
  messages.addFirst(msg);
  final int maxCnt=Symphonys.CHATROOMMSGS_CNT;
  if (messages.size() > maxCnt) {
    messages.remove(maxCnt);
  }
  final JSONObject pushMsg=JSONs.clone(msg);
  pushMsg.put(Common.TIME,Times.getTimeAgo(msg.optLong(Common.TIME),Locales.getLocale()));
  ChatroomChannel.notifyChat(pushMsg);
  context.renderTrueResult();
  try {
    final String userId=currentUser.optString(Keys.OBJECT_ID);
    final JSONObject user=userQueryService.getUser(userId);
    user.put(UserExt.USER_LATEST_CMT_TIME,System.currentTimeMillis());
    userMgmtService.updateUser(userId,user);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Update user latest comment time failed",e);
  }
}
