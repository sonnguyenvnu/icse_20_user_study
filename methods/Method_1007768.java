/** 
 * Performs a redo
 * @param newBlockBag a new block bag
 * @param player the player
 * @return whether anything was redone
 */
public EditSession redo(@Nullable BlockBag newBlockBag,Player player){
  checkNotNull(player);
  if (historyPointer < history.size()) {
    EditSession editSession=history.get(historyPointer);
    try (EditSession newEditSession=WorldEdit.getInstance().getEditSessionFactory().getEditSession(editSession.getWorld(),-1,newBlockBag,player)){
      newEditSession.enableStandardMode();
      newEditSession.setReorderMode(reorderMode);
      newEditSession.setFastMode(fastMode);
      if (newEditSession.getSurvivalExtent() != null) {
        newEditSession.getSurvivalExtent().setStripNbt(!player.hasPermission("worldedit.setnbt"));
      }
      editSession.redo(newEditSession);
    }
     ++historyPointer;
    return editSession;
  }
  return null;
}
