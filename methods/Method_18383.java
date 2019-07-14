private MountItem mountContent(int index,Component component,Object content,ComponentHost host,LayoutOutput layoutOutput){
  final MountItem item=new MountItem(component,host,content,layoutOutput);
  mIndexToItemMap.put(mLayoutOutputsIds[index],item);
  if (component.hasChildLithoViews()) {
    mCanMountIncrementallyMountItems.put(mLayoutOutputsIds[index],item);
  }
  layoutOutput.getMountBounds(sTempRect);
  host.mount(index,item,sTempRect);
  setViewAttributes(item);
  return item;
}
