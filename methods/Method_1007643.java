@Command(name="/navwand",aliases="navwand",desc="Navigation wand tool") @CommandPermissions("worldedit.setwand") public void navwand(Player player,LocalSession session) throws WorldEditException {
  BaseItemStack itemStack=player.getItemInHand(HandSide.MAIN_HAND);
  session.setTool(itemStack.getType(),new NavigationWand());
  player.print("Navigation wand bound to " + itemStack.getType().getName() + ".");
}
