@Override public void notifyDataSetChanged(){
  hasHints=folderId == 0 && dialogsType == 0 && !isOnlySelect && !MessagesController.getInstance(currentAccount).hintDialogs.isEmpty();
  super.notifyDataSetChanged();
}
