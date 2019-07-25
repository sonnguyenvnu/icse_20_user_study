@Command(name="descend",aliases={"desc"},desc="Go down a floor") @CommandPermissions("worldedit.navigation.descend") public void descend(Player player,@Arg(desc="# of levels to descend",def="1") int levels) throws WorldEditException {
  int descentLevels=0;
  while (player.descendLevel()) {
    ++descentLevels;
    if (levels == descentLevels) {
      break;
    }
  }
  if (descentLevels == 0) {
    player.printError("No free spot below you found.");
  }
 else {
    player.print((descentLevels != 1) ? "Descended " + descentLevels + " levels." : "Descended a level.");
  }
}
