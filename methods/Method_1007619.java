@Command(name="/stack",desc="Repeat the contents of the selection") @CommandPermissions("worldedit.region.stack") @Logging(ORIENTATION_REGION) public int stack(Player player,EditSession editSession,LocalSession session,@Selection Region region,@Arg(desc="# of copies to stack",def="1") int count,@Arg(desc="The direction to stack",def=Direction.AIM) @Direction(includeDiagonals=true) BlockVector3 direction,@Switch(name='s',desc="Shift the selection to the last stacked copy") boolean moveSelection,@Switch(name='a',desc="Ignore air blocks") boolean ignoreAirBlocks) throws WorldEditException {
  int affected=editSession.stackCuboidRegion(region,direction,count,!ignoreAirBlocks);
  if (moveSelection) {
    try {
      final BlockVector3 size=region.getMaximumPoint().subtract(region.getMinimumPoint());
      final BlockVector3 shiftVector=direction.toVector3().multiply(count * (Math.abs(direction.dot(size)) + 1)).toBlockPoint();
      region.shift(shiftVector);
      session.getRegionSelector(player.getWorld()).learnChanges();
      session.getRegionSelector(player.getWorld()).explainRegionAdjust(player,session);
    }
 catch (    RegionOperationException e) {
      player.printError(e.getMessage());
    }
  }
  player.print(affected + " block(s) changed. Undo with //undo");
  return affected;
}
