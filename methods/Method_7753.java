@Override public void notifyItemMoved(int fromPosition,int toPosition){
  ArrayList<TLRPC.Dialog> dialogs=DialogsActivity.getDialogsArray(currentAccount,dialogsType,folderId,false);
  int fromIndex=fixPosition(fromPosition);
  int toIndex=fixPosition(toPosition);
  TLRPC.Dialog fromDialog=dialogs.get(fromIndex);
  TLRPC.Dialog toDialog=dialogs.get(toIndex);
  int oldNum=fromDialog.pinnedNum;
  fromDialog.pinnedNum=toDialog.pinnedNum;
  toDialog.pinnedNum=oldNum;
  Collections.swap(dialogs,fromIndex,toIndex);
  super.notifyItemMoved(fromPosition,toPosition);
}
