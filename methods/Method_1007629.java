@Command(name="area",desc="Enable the area super pickaxe pickaxe mode") @CommandPermissions("worldedit.superpickaxe.area") public void area(Player player,LocalSession session,@Arg(desc="The range of the area pickaxe") int range) throws WorldEditException {
  LocalConfiguration config=we.getConfiguration();
  if (range > config.maxSuperPickaxeSize) {
    player.printError("Maximum range: " + config.maxSuperPickaxeSize);
    return;
  }
  session.setSuperPickaxe(new AreaPickaxe(range));
  session.enableSuperPickAxe();
  player.print("Mode changed. Left click with a pickaxe. // to disable.");
}
