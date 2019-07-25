@Command(name="cycler",desc="Block data cycler tool") @CommandPermissions("worldedit.tool.data-cycler") public void cycler(Player player,LocalSession session) throws WorldEditException {
  BaseItemStack itemStack=player.getItemInHand(HandSide.MAIN_HAND);
  session.setTool(itemStack.getType(),new BlockDataCyler());
  player.print("Block data cycler tool bound to " + itemStack.getType().getName() + ".");
}
