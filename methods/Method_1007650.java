@Command(name="material",aliases={"/material"},desc="Set the brush material") @CommandPermissions("worldedit.brush.options.material") public void material(Player player,LocalSession session,@Arg(desc="The pattern of blocks to use") Pattern pattern) throws WorldEditException {
  session.getBrushTool(player.getItemInHand(HandSide.MAIN_HAND).getType()).setFill(pattern);
  player.print("Brush material set.");
}
