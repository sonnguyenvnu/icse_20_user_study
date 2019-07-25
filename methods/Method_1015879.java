@Override public void handle(TabCompleteResponse tabCompleteResponse) throws Exception {
  List<String> commands=tabCompleteResponse.getCommands();
  if (commands == null) {
    commands=Lists.transform(tabCompleteResponse.getSuggestions().getList(),new Function<Suggestion,String>(){
      @Override public String apply(      Suggestion input){
        return input.getText();
      }
    }
);
  }
  TabCompleteResponseEvent tabCompleteResponseEvent=new TabCompleteResponseEvent(server,con,new ArrayList<>(commands));
  if (!bungee.getPluginManager().callEvent(tabCompleteResponseEvent).isCancelled()) {
    if (!commands.equals(tabCompleteResponseEvent.getSuggestions())) {
      if (tabCompleteResponse.getCommands() != null) {
        tabCompleteResponse.setCommands(tabCompleteResponseEvent.getSuggestions());
      }
 else {
        final StringRange range=tabCompleteResponse.getSuggestions().getRange();
        tabCompleteResponse.setSuggestions(new Suggestions(range,Lists.transform(tabCompleteResponseEvent.getSuggestions(),new Function<String,Suggestion>(){
          @Override public Suggestion apply(          String input){
            return new Suggestion(range,input);
          }
        }
)));
      }
    }
    con.unsafe().sendPacket(tabCompleteResponse);
  }
  throw CancelSendSignal.INSTANCE;
}
