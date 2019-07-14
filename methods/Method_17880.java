private boolean implementsVirtualViews(){
  MountItem item=getAccessibleMountItem();
  return item != null && item.getComponent().implementsExtraAccessibilityNodes();
}
