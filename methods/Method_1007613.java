@Command(name="/pyramid",desc="Generate a filled pyramid") @CommandPermissions("worldedit.generation.pyramid") @Logging(PLACEMENT) public int pyramid(Player player,LocalSession session,EditSession editSession,@Arg(desc="The pattern of blocks to set") Pattern pattern,@Arg(desc="The size of the pyramid") int size,@Switch(name='h',desc="Make a hollow pyramid") boolean hollow) throws WorldEditException {
  BlockVector3 pos=session.getPlacementPosition(player);
  worldEdit.checkMaxRadius(size);
  int affected=editSession.makePyramid(pos,pattern,size,!hollow);
  player.findFreePosition();
  player.print(affected + " block(s) have been created.");
  return affected;
}
