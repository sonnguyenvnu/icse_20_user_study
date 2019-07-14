private void hideActionMode(boolean animateCheck){
  actionBar.hideActionMode();
  if (menuDrawable != null) {
    actionBar.setBackButtonContentDescription(LocaleController.getString("AccDescrOpenMenu",R.string.AccDescrOpenMenu));
  }
  dialogsAdapter.getSelectedDialogs().clear();
  if (menuDrawable != null) {
    menuDrawable.setRotation(0,true);
  }
 else   if (backDrawable != null) {
    backDrawable.setRotation(0,true);
  }
  allowMoving=false;
  if (movingWas) {
    getMessagesController().reorderPinnedDialogs(folderId,null,0);
    movingWas=false;
  }
  updateCounters(true);
  dialogsAdapter.onReorderStateChanged(false);
  updateVisibleRows(MessagesController.UPDATE_MASK_REORDER | MessagesController.UPDATE_MASK_CHECK | (animateCheck ? MessagesController.UPDATE_MASK_CHAT : 0));
}
