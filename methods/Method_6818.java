public void setLastCreatedDialogId(final long dialogId,final boolean set){
  if (set) {
    if (createdDialogMainThreadIds.contains(dialogId)) {
      return;
    }
    createdDialogMainThreadIds.add(dialogId);
  }
 else {
    createdDialogMainThreadIds.remove(dialogId);
    SparseArray<MessageObject> array=pollsToCheck.get(dialogId);
    if (array != null) {
      for (int a=0, N=array.size(); a < N; a++) {
        MessageObject object=array.valueAt(a);
        object.pollVisibleOnScreen=false;
      }
    }
  }
  Utilities.stageQueue.postRunnable(() -> {
    if (set) {
      if (createdDialogIds.contains(dialogId)) {
        return;
      }
      createdDialogIds.add(dialogId);
    }
 else {
      createdDialogIds.remove(dialogId);
    }
  }
);
}
