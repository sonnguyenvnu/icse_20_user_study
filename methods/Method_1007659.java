@Command(name="snow",aliases={"/snow"},desc="Simulates snow") @CommandPermissions("worldedit.snow") @Logging(PLACEMENT) public int snow(Player player,LocalSession session,EditSession editSession,@Arg(desc="The radius of the circle to snow in",def="10") double size) throws WorldEditException {
  size=Math.max(1,size);
  we.checkMaxRadius(size);
  int affected=editSession.simulateSnow(session.getPlacementPosition(player),size);
  player.print(affected + " surface(s) covered. Let it snow~");
  return affected;
}
