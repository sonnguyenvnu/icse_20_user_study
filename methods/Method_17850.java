/** 
 * Mounts the given  {@link MountItem} with unique index.
 * @param index index of the {@link MountItem}. Guaranteed to be the same index as is passed for the corresponding  {@code unmount(index, mountItem)} call.
 * @param mountItem item to be mounted into the host.
 * @param bounds the bounds of the item that is to be mounted into the host
 */
public void mount(int index,MountItem mountItem,Rect bounds){
  final Object content=mountItem.getContent();
  if (content instanceof Drawable) {
    mountDrawable(index,mountItem,bounds);
  }
 else   if (content instanceof View) {
    ensureViewMountItems();
    mViewMountItems.put(index,mountItem);
    mountView((View)content,mountItem.getLayoutFlags());
    maybeRegisterTouchExpansion(index,mountItem);
  }
  ensureMountItems();
  mMountItems.put(index,mountItem);
  maybeInvalidateAccessibilityState(mountItem);
}
