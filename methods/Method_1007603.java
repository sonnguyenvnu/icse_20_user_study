@Command(name="deform",desc="Deform brush, applies an expression to an area") @CommandPermissions("worldedit.brush.deform") public void deform(Player player,LocalSession localSession,@Arg(desc="The shape of the region") RegionFactory shape,@Arg(desc="The size of the brush",def="5") double radius,@Arg(desc="Expression to apply",def="y-=0.2") String expression,@Switch(name='r',desc="Use the game's coordinate origin") boolean useRawCoords,@Switch(name='o',desc="Use the placement position as the origin") boolean usePlacement) throws WorldEditException {
  Deform deform=new Deform(expression);
  if (useRawCoords) {
    deform.setMode(Deform.Mode.RAW_COORD);
  }
 else   if (usePlacement) {
    deform.setMode(Deform.Mode.OFFSET);
    deform.setOffset(localSession.getPlacementPosition(player).toVector3());
  }
  setOperationBasedBrush(player,localSession,radius,deform,shape,"worldedit.brush.deform");
}
