private void openAddMember(){
  Bundle args=new Bundle();
  args.putBoolean("addToGroup",true);
  args.putInt("chatId",currentChat.id);
  GroupCreateActivity fragment=new GroupCreateActivity(args);
  if (chatInfo != null && chatInfo.participants != null) {
    SparseArray<TLObject> users=new SparseArray<>();
    for (int a=0; a < chatInfo.participants.participants.size(); a++) {
      users.put(chatInfo.participants.participants.get(a).user_id,null);
    }
    fragment.setIgnoreUsers(users);
  }
  fragment.setDelegate((users,fwdCount) -> {
    for (int a=0, N=users.size(); a < N; a++) {
      TLRPC.User user=users.get(a);
      MessagesController.getInstance(currentAccount).addUserToChat(chat_id,user,chatInfo,fwdCount,null,ProfileActivity.this,null);
    }
  }
);
  presentFragment(fragment);
}
