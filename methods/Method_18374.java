private static boolean sameSize(LayoutOutput layoutOutput,MountItem item){
  final Rect layoutOutputBounds=layoutOutput.getBounds();
  final Object mountedContent=item.getContent();
  return layoutOutputBounds.width() == getWidthForMountedContent(mountedContent) && layoutOutputBounds.height() == getHeightForMountedContent(mountedContent);
}
