@Command(name="tree",desc="Tree generator tool") @CommandPermissions("worldedit.tool.tree") public void tree(Player player,LocalSession session,@Arg(desc="Type of tree to generate",def="tree") TreeGenerator.TreeType type) throws WorldEditException {
  BaseItemStack itemStack=player.getItemInHand(HandSide.MAIN_HAND);
  session.setTool(itemStack.getType(),new TreePlanter(type));
  player.print("Tree tool bound to " + itemStack.getType().getName() + ".");
}
