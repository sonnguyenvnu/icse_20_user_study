@Command(name="thru",desc="Pass through walls") @CommandPermissions("worldedit.navigation.thru.command") public void thru(Player player) throws WorldEditException {
  if (player.passThroughForwardWall(6)) {
    player.print("Whoosh!");
  }
 else {
    player.printError("No free spot ahead of you found.");
  }
}
