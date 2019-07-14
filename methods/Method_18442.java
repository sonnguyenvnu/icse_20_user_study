/** 
 * This is called when the  {@link MountItem}s mounted on this  {@link MountState} need to bere-bound with the same component. The common case here is a detach/attach happens on the {@link LithoView} that owns the MountState.
 */
void rebind(){
  assertMainThread();
  if (mLayoutOutputsIds == null) {
    return;
  }
  for (int i=0, size=mLayoutOutputsIds.length; i < size; i++) {
    final MountItem mountItem=getItemAt(i);
    if (mountItem == null || mountItem.isBound()) {
      continue;
    }
    final Component component=mountItem.getComponent();
    final Object content=mountItem.getContent();
    bindComponentToContent(component,content);
    mountItem.setIsBound(true);
    if (content instanceof View && !(content instanceof ComponentHost) && ((View)content).isLayoutRequested()) {
      final View view=(View)content;
      applyBoundsToMountContent(view,view.getLeft(),view.getTop(),view.getRight(),view.getBottom(),true);
    }
  }
}
