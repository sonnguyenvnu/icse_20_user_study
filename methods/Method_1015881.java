@Override public void handle(Commands commands) throws Exception {
  boolean modified=false;
  for (  Map.Entry<String,Command> command : bungee.getPluginManager().getCommands()) {
    if (!bungee.getDisabledCommands().contains(command.getKey()) && commands.getRoot().getChild(command.getKey()) == null && command.getValue().hasPermission(con)) {
      LiteralCommandNode dummy=LiteralArgumentBuilder.literal(command.getKey()).then(RequiredArgumentBuilder.argument("args",StringArgumentType.greedyString()).suggests(Commands.SuggestionRegistry.ASK_SERVER)).build();
      commands.getRoot().addChild(dummy);
      modified=true;
    }
  }
  if (modified) {
    con.unsafe().sendPacket(commands);
    throw CancelSendSignal.INSTANCE;
  }
}
