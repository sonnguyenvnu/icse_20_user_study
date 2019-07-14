/** 
 * Notifies the specified article heat message to browsers.
 * @param message the specified message, for example,"articleId": "", "operation": "" // "+"/"-"
 */
public static void notifyHeat(final JSONObject message){
  message.put(Common.TYPE,Article.ARTICLE_T_HEAT);
  final String msgStr=message.toString();
  for (  final Session session : SESSIONS) {
    final String viewingArticleId=Channels.getHttpParameter(session,Article.ARTICLE_T_ID);
    if (StringUtils.isBlank(viewingArticleId) || !viewingArticleId.equals(message.optString(Article.ARTICLE_T_ID))) {
      continue;
    }
    if (session.isOpen()) {
      session.getAsyncRemote().sendText(msgStr);
    }
  }
}
