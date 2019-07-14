public static void openChatOrProfileWith(TLRPC.User user,TLRPC.Chat chat,BaseFragment fragment,int type,boolean closeLast){
  if (user == null && chat == null || fragment == null) {
    return;
  }
  String reason=null;
  if (chat != null) {
    reason=getRestrictionReason(chat.restriction_reason);
  }
 else   if (user != null) {
    reason=getRestrictionReason(user.restriction_reason);
    if (user.bot) {
      type=1;
      closeLast=true;
    }
  }
  if (reason != null) {
    showCantOpenAlert(fragment,reason);
  }
 else {
    Bundle args=new Bundle();
    if (chat != null) {
      args.putInt("chat_id",chat.id);
    }
 else {
      args.putInt("user_id",user.id);
    }
    if (type == 0) {
      fragment.presentFragment(new ProfileActivity(args));
    }
 else     if (type == 2) {
      fragment.presentFragment(new ChatActivity(args),true,true);
    }
 else {
      fragment.presentFragment(new ChatActivity(args),closeLast);
    }
  }
}
