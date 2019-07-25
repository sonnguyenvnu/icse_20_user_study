@Command(name="single",desc="Enable the single block super pickaxe mode") @CommandPermissions("worldedit.superpickaxe") public void single(Player player,LocalSession session) throws WorldEditException {
  session.setSuperPickaxe(new SinglePickaxe());
  session.enableSuperPickAxe();
  player.print("Mode changed. Left click with a pickaxe. // to disable.");
}
