@Override public void handle(TabCompleteRequest tabComplete) throws Exception {
  List<String> suggestions=new ArrayList<>();
  if (tabComplete.getCursor().startsWith("/")) {
    bungee.getPluginManager().dispatchCommand(con,tabComplete.getCursor().substring(1),suggestions);
  }
  TabCompleteEvent tabCompleteEvent=new TabCompleteEvent(con,con.getServer(),tabComplete.getCursor(),suggestions);
  bungee.getPluginManager().callEvent(tabCompleteEvent);
  if (tabCompleteEvent.isCancelled()) {
    throw CancelSendSignal.INSTANCE;
  }
  List<String> results=tabCompleteEvent.getSuggestions();
  if (!results.isEmpty()) {
    if (con.getPendingConnection().getVersion() < ProtocolConstants.MINECRAFT_1_13) {
      con.unsafe().sendPacket(new TabCompleteResponse(results));
    }
 else {
      int start=tabComplete.getCursor().lastIndexOf(' ') + 1;
      int end=tabComplete.getCursor().length();
      StringRange range=StringRange.between(start,end);
      List<Suggestion> brigadier=new LinkedList<>();
      for (      String s : results) {
        brigadier.add(new Suggestion(range,s));
      }
      con.unsafe().sendPacket(new TabCompleteResponse(tabComplete.getTransactionId(),new Suggestions(range,brigadier)));
    }
    throw CancelSendSignal.INSTANCE;
  }
}
