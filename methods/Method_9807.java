/** 
 * Called when the connection closed.
 * @param session     session
 * @param closeReason close reason
 */
@OnClose public void onClose(final Session session,final CloseReason closeReason){
  removeSession(session);
}
