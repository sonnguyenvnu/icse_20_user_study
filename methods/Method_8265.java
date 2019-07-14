private void updateBotButtons(){
  if (headerItem == null || currentUser == null || currentEncryptedChat != null || !currentUser.bot) {
    return;
  }
  boolean hasHelp=false;
  boolean hasSettings=false;
  if (botInfo.size() != 0) {
    for (int b=0; b < botInfo.size(); b++) {
      TLRPC.BotInfo info=botInfo.valueAt(b);
      for (int a=0; a < info.commands.size(); a++) {
        TLRPC.TL_botCommand command=info.commands.get(a);
        if (command.command.toLowerCase().equals("help")) {
          hasHelp=true;
        }
 else         if (command.command.toLowerCase().equals("settings")) {
          hasSettings=true;
        }
        if (hasSettings && hasHelp) {
          break;
        }
      }
    }
  }
  if (hasHelp) {
    headerItem.showSubItem(bot_help);
  }
 else {
    headerItem.hideSubItem(bot_help);
  }
  if (hasSettings) {
    headerItem.showSubItem(bot_settings);
  }
 else {
    headerItem.hideSubItem(bot_settings);
  }
}
