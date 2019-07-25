@Command(name="info",desc="Block information tool") @CommandPermissions("worldedit.tool.info") public void info(Player player,LocalSession session) throws WorldEditException {
  BaseItemStack itemStack=player.getItemInHand(HandSide.MAIN_HAND);
  session.setTool(itemStack.getType(),new QueryTool());
  player.print("Info tool bound to " + itemStack.getType().getName() + ".");
}
