@Override public void execute(CommandSender sender,String[] args){
  if (args.length == 0) {
    sender.sendMessage(ChatColor.RED + "You must supply a message.");
  }
 else {
    String message=Joiner.on(' ').join(args);
    try {
      ProxyServer.getInstance().broadcast(ComponentSerializer.parse(message));
    }
 catch (    Exception e) {
      Throwable error=e;
      while (error.getCause() != null) {
        error=error.getCause();
      }
      if (sender instanceof ProxiedPlayer) {
        sender.sendMessage(new ComponentBuilder("An error occurred while parsing your message. (Hover for details)").color(ChatColor.RED).underlined(true).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(error.getMessage()).color(ChatColor.RED).create())).create());
      }
 else {
        sender.sendMessage(new ComponentBuilder("An error occurred while parsing your message: ").color(ChatColor.RED).append(error.getMessage()).create());
      }
    }
  }
}
