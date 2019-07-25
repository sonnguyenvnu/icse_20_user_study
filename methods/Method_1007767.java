/** 
 * Performs an undo.
 * @param newBlockBag a new block bag
 * @param player the player
 * @return whether anything was undone
 */
public EditSession undo(@Nullable BlockBag newBlockBag,Player player){
  checkNotNull(player);
  --historyPointer;
  if (historyPointer >= 0) {
    EditSession editSession=history.get(historyPointer);
    try (EditSession newEditSession=WorldEdit.getInstance().getEditSessionFactory().getEditSession(editSession.getWorld(),-1,newBlockBag,player)){
      newEditSession.enableStandardMode();
      newEditSession.setReorderMode(reorderMode);
      newEditSession.setFastMode(fastMode);
      if (newEditSession.getSurvivalExtent() != null) {
        newEditSession.getSurvivalExtent().setStripNbt(!player.hasPermission("worldedit.setnbt"));
      }
      editSession.undo(newEditSession);
    }
     return editSession;
  }
 else {
    historyPointer=0;
    return null;
  }
}
