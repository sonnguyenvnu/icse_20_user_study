@Command(name="/drain",desc="Drain a pool") @CommandPermissions("worldedit.drain") @Logging(PLACEMENT) public int drain(Player player,LocalSession session,EditSession editSession,@Arg(desc="The radius to drain") double radius,@Switch(name='w',desc="Also un-waterlog blocks") boolean waterlogged) throws WorldEditException {
  radius=Math.max(0,radius);
  we.checkMaxRadius(radius);
  int affected=editSession.drainArea(session.getPlacementPosition(player),radius,waterlogged);
  player.print(affected + " block(s) have been changed.");
  return affected;
}
