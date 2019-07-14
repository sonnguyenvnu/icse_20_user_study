public void setOpenedDialogId(final long dialog_id){
  notificationsQueue.postRunnable(() -> opened_dialog_id=dialog_id);
}
