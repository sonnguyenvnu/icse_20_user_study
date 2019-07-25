@Command(name="/naturalize",desc="3 layers of dirt on top then rock below") @CommandPermissions("worldedit.region.naturalize") @Logging(REGION) public int naturalize(Player player,EditSession editSession,@Selection Region region) throws WorldEditException {
  int affected=editSession.naturalizeCuboidBlocks(region);
  player.print(affected + " block(s) have been made to look more natural.");
  return affected;
}
