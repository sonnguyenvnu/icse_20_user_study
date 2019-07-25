/** 
 * Get an edit session. Every subsequent call returns a new edit session. Usually you only need to use one edit session.
 * @return an edit session
 */
public EditSession remember(){
  EditSession editSession=controller.getEditSessionFactory().getEditSession(player.getWorld(),session.getBlockChangeLimit(),session.getBlockBag(player),player);
  Request.request().setEditSession(editSession);
  editSession.enableStandardMode();
  editSessions.add(editSession);
  return editSession;
}
