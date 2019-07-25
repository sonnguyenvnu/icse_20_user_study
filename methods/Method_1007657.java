@Command(name="/fillr",desc="Fill a hole recursively") @CommandPermissions("worldedit.fill.recursive") @Logging(PLACEMENT) public int fillr(Player player,LocalSession session,EditSession editSession,@Arg(desc="The blocks to fill with") Pattern pattern,@Arg(desc="The radius to fill in") double radius,@Arg(desc="The depth to fill",def="") Integer depth) throws WorldEditException {
  radius=Math.max(1,radius);
  we.checkMaxRadius(radius);
  depth=depth == null ? Integer.MAX_VALUE : Math.max(1,depth);
  we.checkMaxRadius(radius);
  BlockVector3 pos=session.getPlacementPosition(player);
  int affected=editSession.fillXZ(pos,pattern,radius,depth,true);
  player.print(affected + " block(s) have been created.");
  return affected;
}
