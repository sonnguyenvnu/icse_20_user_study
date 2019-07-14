public void registerUndo(UUID uuid,Runnable undoRunnable){
  uuidToOperationMap.put(uuid,undoRunnable);
  operations.add(uuid);
  notifyOfHistoryChanges();
}
