/** 
 * Notifies the specified chat message to browsers.
 * @param message the specified message, for example,{ "userName": "", "content": "" }
 */
public static void notifyChat(final JSONObject message){
  message.put(Common.TYPE,"msg");
  final String msgStr=message.toString();
synchronized (SESSIONS) {
    final Iterator<Session> i=SESSIONS.iterator();
    while (i.hasNext()) {
      final Session session=i.next();
      if (session.isOpen()) {
        session.getAsyncRemote().sendText(msgStr);
      }
    }
  }
}
