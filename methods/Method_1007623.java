@Command(name="/chunk",desc="Set the selection to your current chunk.") @Logging(POSITION) @CommandPermissions("worldedit.selection.chunk") public void chunk(Player player,LocalSession session,@Arg(desc="The chunk to select",def="") BlockVector2 coordinates,@Switch(name='s',desc="Expand your selection to encompass all chunks that are part of it") boolean expandSelection,@Switch(name='c',desc="Use chunk coordinates instead of block coordinates") boolean useChunkCoordinates) throws WorldEditException {
  final BlockVector3 min;
  final BlockVector3 max;
  final World world=player.getWorld();
  if (expandSelection) {
    Region region=session.getSelection(world);
    final BlockVector2 min2D=ChunkStore.toChunk(region.getMinimumPoint());
    final BlockVector2 max2D=ChunkStore.toChunk(region.getMaximumPoint());
    min=BlockVector3.at(min2D.getBlockX() * 16,0,min2D.getBlockZ() * 16);
    max=BlockVector3.at(max2D.getBlockX() * 16 + 15,world.getMaxY(),max2D.getBlockZ() * 16 + 15);
    player.print("Chunks selected: (" + min2D.getBlockX() + ", " + min2D.getBlockZ() + ") - (" + max2D.getBlockX() + ", " + max2D.getBlockZ() + ")");
  }
 else {
    final BlockVector2 min2D;
    if (coordinates != null) {
      min2D=useChunkCoordinates ? coordinates : ChunkStore.toChunk(coordinates.toBlockVector3());
    }
 else {
      min2D=ChunkStore.toChunk(player.getBlockIn().toVector().toBlockPoint());
    }
    min=BlockVector3.at(min2D.getBlockX() * 16,0,min2D.getBlockZ() * 16);
    max=min.add(15,world.getMaxY(),15);
    player.print("Chunk selected: " + min2D.getBlockX() + ", " + min2D.getBlockZ());
  }
  final CuboidRegionSelector selector;
  if (session.getRegionSelector(world) instanceof ExtendingCuboidRegionSelector) {
    selector=new ExtendingCuboidRegionSelector(world);
  }
 else {
    selector=new CuboidRegionSelector(world);
  }
  selector.selectPrimary(min,ActorSelectorLimits.forActor(player));
  selector.selectSecondary(max,ActorSelectorLimits.forActor(player));
  session.setRegionSelector(world,selector);
  session.dispatchCUISelection(player);
}
