@Command(name="deltree",desc="Floating tree remover tool") @CommandPermissions("worldedit.tool.deltree") public void deltree(Player player,LocalSession session) throws WorldEditException {
  BaseItemStack itemStack=player.getItemInHand(HandSide.MAIN_HAND);
  session.setTool(itemStack.getType(),new FloatingTreeRemover());
  player.print("Floating tree remover tool bound to " + itemStack.getType().getName() + ".");
}
