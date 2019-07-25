@Command(name="/limit",desc="Modify block change limit") @CommandPermissions("worldedit.limit") public void limit(Player player,LocalSession session,@Arg(desc="The limit to set",def="") Integer limit){
  LocalConfiguration config=worldEdit.getConfiguration();
  boolean mayDisable=player.hasPermission("worldedit.limit.unrestricted");
  limit=limit == null ? config.defaultChangeLimit : Math.max(-1,limit);
  if (!mayDisable && config.maxChangeLimit > -1) {
    if (limit > config.maxChangeLimit) {
      player.printError("Your maximum allowable limit is " + config.maxChangeLimit + ".");
      return;
    }
  }
  session.setBlockChangeLimit(limit);
  player.print("Block change limit set to " + limit + "." + (limit == config.defaultChangeLimit ? "" : " (Use //limit to go back to the default.)"));
}
