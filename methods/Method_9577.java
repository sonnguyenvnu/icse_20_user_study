private void leaveChatPressed(){
  AlertsCreator.createClearOrDeleteDialogAlert(ProfileActivity.this,false,currentChat,null,false,(param) -> {
    playProfileAnimation=false;
    NotificationCenter.getInstance(currentAccount).removeObserver(ProfileActivity.this,NotificationCenter.closeChats);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
    finishFragment();
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needDeleteDialog,(long)-currentChat.id,null,currentChat,param);
  }
);
}
