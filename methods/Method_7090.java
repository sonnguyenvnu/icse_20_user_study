public void removeNotificationsForDialog(long did){
  NotificationsController.getInstance(currentAccount).processReadMessages(null,did,0,Integer.MAX_VALUE,false);
  LongSparseArray<Integer> dialogsToUpdate=new LongSparseArray<>();
  dialogsToUpdate.put(did,0);
  NotificationsController.getInstance(currentAccount).processDialogsUpdateRead(dialogsToUpdate);
}
