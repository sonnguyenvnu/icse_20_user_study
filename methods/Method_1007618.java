@Command(name="/faces",aliases={"/outline"},desc="Build the walls, ceiling, and floor of a selection") @CommandPermissions("worldedit.region.faces") @Logging(REGION) public int faces(Player player,EditSession editSession,@Selection Region region,@Arg(desc="The pattern of blocks to set") Pattern pattern) throws WorldEditException {
  int affected=editSession.makeCuboidFaces(region,pattern);
  player.print(affected + " block(s) have been changed.");
  return affected;
}
