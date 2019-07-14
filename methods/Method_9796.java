/** 
 * Notifies the specified article heat message to browsers.
 * @param message the specified message, for example{ "articleId": "", "operation": "" // "+"/"-" }
 */
public static void notifyHeat(final JSONObject message){
  final String articleId=message.optString(Article.ARTICLE_T_ID);
  final String msgStr=message.toString();
  for (  final Map.Entry<Session,String> entry : SESSIONS.entrySet()) {
    final Session session=entry.getKey();
    final String articleIds=entry.getValue();
    if (!StringUtils.contains(articleIds,articleId)) {
      continue;
    }
    if (session.isOpen()) {
      session.getAsyncRemote().sendText(msgStr);
    }
  }
}
