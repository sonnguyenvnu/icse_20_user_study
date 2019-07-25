@Override public void handle(PlayerListItem playerList) throws Exception {
  con.getTabListHandler().onUpdate(TabList.rewrite(playerList));
  throw CancelSendSignal.INSTANCE;
}
