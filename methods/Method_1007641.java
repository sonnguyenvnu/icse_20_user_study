@Command(name="none",desc="Unbind a bound tool from your current item") public void none(Player player,LocalSession session) throws WorldEditException {
  session.setTool(player.getItemInHand(HandSide.MAIN_HAND).getType(),null);
  player.print("Tool unbound from your current item.");
}
