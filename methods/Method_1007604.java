@Command(name="set",desc="Set brush, sets all blocks in the area") @CommandPermissions("worldedit.brush.set") public void set(Player player,LocalSession localSession,@Arg(desc="The shape of the region") RegionFactory shape,@Arg(desc="The size of the brush",def="5") double radius,@Arg(desc="The pattern of blocks to set") Pattern pattern) throws WorldEditException {
  setOperationBasedBrush(player,localSession,radius,new Apply(new ReplaceFactory(pattern)),shape,"worldedit.brush.set");
}
