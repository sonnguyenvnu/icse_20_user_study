private void remountComponentHostToRootIfNeeded(int index){
  final ComponentHost rootHost=mHostsByMarker.get(ROOT_HOST_ID);
  final MountItem item=getItemAt(index);
  if (item.getHost() == rootHost) {
    return;
  }
  final Object content=item.getContent();
  int left=0;
  int top=0;
  int right;
  int bottom;
  ComponentHost componentHost=item.getHost();
  while (componentHost != rootHost) {
    left+=componentHost.getLeft();
    top+=componentHost.getTop();
    componentHost=(ComponentHost)componentHost.getParent();
  }
  if (content instanceof View) {
    final View view=(View)content;
    left+=view.getLeft();
    top+=view.getTop();
    right=left + view.getWidth();
    bottom=top + view.getHeight();
  }
 else {
    final Rect bounds=((Drawable)content).getBounds();
    left+=bounds.left;
    right=left + bounds.width();
    top+=bounds.top;
    bottom=top + bounds.height();
  }
  item.getHost().unmount(index,item);
  applyBoundsToMountContent(content,left,top,right,bottom,false);
  rootHost.mount(index,item,sTempRect);
  item.setHost(rootHost);
}
