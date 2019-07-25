@Command(name="lrbuild",aliases={"/lrbuild"},desc="Long-range building tool") @CommandPermissions("worldedit.tool.lrbuild") public void longrangebuildtool(Player player,LocalSession session,@Arg(desc="Pattern to set on left-click") Pattern primary,@Arg(desc="Pattern to set on right-click") Pattern secondary) throws WorldEditException {
  BaseItemStack itemStack=player.getItemInHand(HandSide.MAIN_HAND);
  session.setTool(itemStack.getType(),new LongRangeBuildTool(primary,secondary));
  player.print("Long-range building tool bound to " + itemStack.getType().getName() + ".");
  String primaryName="pattern";
  String secondaryName="pattern";
  if (primary instanceof BlockPattern) {
    primaryName=((BlockPattern)primary).getBlock().getBlockType().getName();
  }
  if (secondary instanceof BlockPattern) {
    secondaryName=((BlockPattern)secondary).getBlock().getBlockType().getName();
  }
  player.print("Left-click set to " + primaryName + "; right-click set to " + secondaryName + ".");
}
