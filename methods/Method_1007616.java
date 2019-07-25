@Command(name="/overlay",desc="Set a block on top of blocks in the region") @CommandPermissions("worldedit.region.overlay") @Logging(REGION) public int overlay(Player player,EditSession editSession,@Selection Region region,@Arg(desc="The pattern of blocks to overlay") Pattern pattern) throws WorldEditException {
  int affected=editSession.overlayCuboidBlocks(region,pattern);
  player.print(affected + " block(s) have been overlaid.");
  return affected;
}
