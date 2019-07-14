public void setLastVisibleDialogId(final long dialog_id,final boolean set){
  if (set) {
    if (visibleDialogMainThreadIds.contains(dialog_id)) {
      return;
    }
    visibleDialogMainThreadIds.add(dialog_id);
  }
 else {
    visibleDialogMainThreadIds.remove(dialog_id);
  }
}
