@Command(name="size",desc="Set the brush size") @CommandPermissions("worldedit.brush.options.size") public void size(Player player,LocalSession session,@Arg(desc="The size of the brush") int size) throws WorldEditException {
  we.checkMaxBrushRadius(size);
  session.getBrushTool(player.getItemInHand(HandSide.MAIN_HAND).getType()).setSize(size);
  player.print("Brush size set.");
}
