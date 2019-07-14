private void mountDrawable(int index,MountItem mountItem,Rect bounds){
  assertMainThread();
  ensureDrawableMountItems();
  mDrawableMountItems.put(index,mountItem);
  final Drawable drawable=(Drawable)mountItem.getContent();
  ComponentHostUtils.mountDrawable(this,drawable,bounds,mountItem.getLayoutFlags(),mountItem.getNodeInfo());
}
