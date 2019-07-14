public static void createClearOrDeleteDialogAlert(BaseFragment fragment,boolean clear,TLRPC.Chat chat,TLRPC.User user,boolean secret,MessagesStorage.BooleanCallback onProcessRunnable){
  createClearOrDeleteDialogAlert(fragment,clear,false,false,chat,user,secret,onProcessRunnable);
}
