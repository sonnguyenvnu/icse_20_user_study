/** 
 * Remember an edit session.
 * @param player a player
 * @param editSession an edit session
 */
public void remember(Player player,EditSession editSession){
  com.sk89q.worldedit.entity.Player wePlayer=wrapPlayer(player);
  LocalSession session=WorldEdit.getInstance().getSessionManager().get(wePlayer);
  session.remember(editSession);
  editSession.flushSession();
  WorldEdit.getInstance().flushBlockBag(wePlayer,editSession);
}
