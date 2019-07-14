public void setBotsCount(int count,boolean hasCommands){
  botCount=count;
  if (hasBotCommands != hasCommands) {
    hasBotCommands=hasCommands;
    updateBotButton();
  }
}
