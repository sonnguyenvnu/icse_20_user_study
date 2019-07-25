@Command(name="/shift",desc="Shift the selection area") @Logging(REGION) @CommandPermissions("worldedit.selection.shift") public void shift(Player player,LocalSession session,@Arg(desc="Amount to shift the selection by") int amount,@Arg(desc="Direction to contract",def=Direction.AIM) @MultiDirection List<BlockVector3> direction) throws WorldEditException {
  try {
    Region region=session.getSelection(player.getWorld());
    for (    BlockVector3 dir : direction) {
      region.shift(dir.multiply(amount));
    }
    session.getRegionSelector(player.getWorld()).learnChanges();
    session.getRegionSelector(player.getWorld()).explainRegionAdjust(player,session);
    player.print("Region shifted.");
  }
 catch (  RegionOperationException e) {
    player.printError(e.getMessage());
  }
}
