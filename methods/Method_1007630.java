@Command(name="recursive",aliases={"recur"},desc="Enable the recursive super pickaxe pickaxe mode") @CommandPermissions("worldedit.superpickaxe.recursive") public void recursive(Player player,LocalSession session,@Arg(desc="The range of the recursive pickaxe") double range) throws WorldEditException {
  LocalConfiguration config=we.getConfiguration();
  if (range > config.maxSuperPickaxeSize) {
    player.printError("Maximum range: " + config.maxSuperPickaxeSize);
    return;
  }
  session.setSuperPickaxe(new RecursivePickaxe(range));
  session.enableSuperPickAxe();
  player.print("Mode changed. Left click with a pickaxe. // to disable.");
}
