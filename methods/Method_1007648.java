@Command(name="farwand",desc="Wand at a distance tool") @CommandPermissions("worldedit.tool.farwand") public void farwand(Player player,LocalSession session) throws WorldEditException {
  BaseItemStack itemStack=player.getItemInHand(HandSide.MAIN_HAND);
  session.setTool(itemStack.getType(),new DistanceWand());
  player.print("Far wand tool bound to " + itemStack.getType().getName() + ".");
}
