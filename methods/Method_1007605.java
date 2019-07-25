@Command(name="forest",desc="Forest brush, creates a forest in the area") @CommandPermissions("worldedit.brush.forest") public void forest(Player player,LocalSession localSession,@Arg(desc="The shape of the region") RegionFactory shape,@Arg(desc="The size of the brush",def="5") double radius,@Arg(desc="The density of the brush",def="20") double density,@Arg(desc="The type of tree to use") TreeGenerator.TreeType type) throws WorldEditException {
  setOperationBasedBrush(player,localSession,radius,new Paint(new TreeGeneratorFactory(type),density / 100),shape,"worldedit.brush.forest");
}
