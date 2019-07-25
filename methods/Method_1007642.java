@Command(name="/selwand",aliases="selwand",desc="Selection wand tool") @CommandPermissions("worldedit.setwand") public void selwand(Player player,LocalSession session) throws WorldEditException {
  final ItemType itemType=player.getItemInHand(HandSide.MAIN_HAND).getType();
  session.setTool(itemType,new SelectionWand());
  player.print("Selection wand bound to " + itemType.getName() + ".");
}
