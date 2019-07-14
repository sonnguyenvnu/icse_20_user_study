public void update(){
  int uid=(int)dialog_id;
  TLRPC.FileLocation photo=null;
  if (uid > 0) {
    currentUser=MessagesController.getInstance(currentAccount).getUser(uid);
    avatarDrawable.setInfo(currentUser);
  }
 else {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-uid);
    avatarDrawable.setInfo(chat);
    currentUser=null;
  }
}
