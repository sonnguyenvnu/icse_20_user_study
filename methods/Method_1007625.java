@Command(name="/size",desc="Get information about the selection") @CommandPermissions("worldedit.selection.size") public void size(Player player,LocalSession session,@Switch(name='c',desc="Get clipboard info instead") boolean clipboardInfo) throws WorldEditException {
  Region region;
  if (clipboardInfo) {
    ClipboardHolder holder=session.getClipboard();
    Clipboard clipboard=holder.getClipboard();
    region=clipboard.getRegion();
    BlockVector3 origin=clipboard.getOrigin();
    player.print("Offset: " + origin);
  }
 else {
    region=session.getSelection(player.getWorld());
    player.print("Type: " + session.getRegionSelector(player.getWorld()).getTypeName());
    for (    String line : session.getRegionSelector(player.getWorld()).getInformationLines()) {
      player.print(line);
    }
  }
  BlockVector3 size=region.getMaximumPoint().subtract(region.getMinimumPoint()).add(1,1,1);
  player.print("Size: " + size);
  player.print("Cuboid distance: " + region.getMaximumPoint().distance(region.getMinimumPoint()));
  player.print("# of blocks: " + region.getArea());
}
