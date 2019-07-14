private void checkBotCommands(){
  URLSpanBotCommand.enabled=false;
  if (currentUser != null && currentUser.bot) {
    URLSpanBotCommand.enabled=true;
  }
 else   if (chatInfo instanceof TLRPC.TL_chatFull) {
    for (int a=0; a < chatInfo.participants.participants.size(); a++) {
      TLRPC.ChatParticipant participant=chatInfo.participants.participants.get(a);
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(participant.user_id);
      if (user != null && user.bot) {
        URLSpanBotCommand.enabled=true;
        break;
      }
    }
  }
 else   if (chatInfo instanceof TLRPC.TL_channelFull) {
    URLSpanBotCommand.enabled=!chatInfo.bot_info.isEmpty() && currentChat != null && currentChat.megagroup;
  }
}
