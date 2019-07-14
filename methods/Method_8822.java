public void unregisterUndo(UUID uuid){
  uuidToOperationMap.remove(uuid);
  operations.remove(uuid);
  notifyOfHistoryChanges();
}
