private void mountLayoutOutput(int index,LayoutOutput layoutOutput,LayoutState layoutState){
  final long startTime=System.nanoTime();
  final long hostMarker=layoutOutput.getHostMarker();
  ComponentHost host=mHostsByMarker.get(hostMarker);
  if (host == null) {
    final int hostMountIndex=layoutState.getLayoutOutputPositionForId(hostMarker);
    final LayoutOutput hostLayoutOutput=layoutState.getMountableOutputAt(hostMountIndex);
    mountLayoutOutput(hostMountIndex,hostLayoutOutput,layoutState);
    host=mHostsByMarker.get(hostMarker);
  }
  final Component component=layoutOutput.getComponent();
  if (component == null) {
    throw new RuntimeException("Trying to mount a LayoutOutput with a null Component.");
  }
  final Object content=ComponentsPools.acquireMountContent(mContext.getAndroidContext(),component);
  final ComponentContext context=getContextForComponent(component);
  component.mount(context,content);
  if (isHostSpec(component)) {
    ComponentHost componentHost=(ComponentHost)content;
    componentHost.setParentHostMarker(hostMarker);
    registerHost(layoutOutput.getId(),componentHost);
  }
  final MountItem item=mountContent(index,component,content,host,layoutOutput);
  bindComponentToContent(component,content);
  item.setIsBound(true);
  getActualBounds(layoutOutput,layoutState,sTempRect);
  applyBoundsToMountContent(item.getContent(),sTempRect.left,sTempRect.top,sTempRect.right,sTempRect.bottom,true);
  if (mMountStats.isLoggingEnabled) {
    mMountStats.mountTimes.add((System.nanoTime() - startTime) / NS_IN_MS);
    mMountStats.mountedNames.add(component.getSimpleName());
    mMountStats.mountedCount++;
    mMountStats.extras.add(LogTreePopulator.getAnnotationBundleFromLogger(component,context.getLogger()));
  }
}
