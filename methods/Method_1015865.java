@Override public void execute(CommandSender sender,String[] args){
  Set<String> permissions=new HashSet<>();
  for (  String group : sender.getGroups()) {
    permissions.addAll(ProxyServer.getInstance().getConfigurationAdapter().getPermissions(group));
  }
  sender.sendMessage(ChatColor.GOLD + "You have the following groups: " + Util.csv(sender.getGroups()));
  for (  String permission : permissions) {
    sender.sendMessage(ChatColor.BLUE + "- " + permission);
  }
}
