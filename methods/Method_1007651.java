@Command(name="range",desc="Set the brush range") @CommandPermissions("worldedit.brush.options.range") public void range(Player player,LocalSession session,@Arg(desc="The range of the brush") int range) throws WorldEditException {
  session.getBrushTool(player.getItemInHand(HandSide.MAIN_HAND).getType()).setRange(range);
  player.print("Brush range set.");
}
