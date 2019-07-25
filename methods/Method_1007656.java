@Command(name="/fill",desc="Fill a hole") @CommandPermissions("worldedit.fill") @Logging(PLACEMENT) public int fill(Player player,LocalSession session,EditSession editSession,@Arg(desc="The blocks to fill with") Pattern pattern,@Arg(desc="The radius to fill in") double radius,@Arg(desc="The depth to fill",def="1") int depth) throws WorldEditException {
  radius=Math.max(1,radius);
  we.checkMaxRadius(radius);
  depth=Math.max(1,depth);
  BlockVector3 pos=session.getPlacementPosition(player);
  int affected=editSession.fillXZ(pos,pattern,radius,depth,false);
  player.print(affected + " block(s) have been created.");
  return affected;
}
