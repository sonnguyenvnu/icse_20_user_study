/** 
 * Called when the socket connection with the browser is established.
 * @param session session
 */
@OnOpen public void onConnect(final Session session){
  final String articleIds=Channels.getHttpParameter(session,Article.ARTICLE_T_IDS);
  if (StringUtils.isBlank(articleIds)) {
    return;
  }
  SESSIONS.put(session,articleIds);
}
