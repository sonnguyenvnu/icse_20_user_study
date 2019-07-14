public void searchMessagesInChat(String query,final long dialog_id,final long mergeDialogId,final int guid,final int direction,TLRPC.User user){
  searchMessagesInChat(query,dialog_id,mergeDialogId,guid,direction,false,user);
}
