private int findLastDescendantIndex(LayoutState layoutState,int index){
  final LayoutOutput host=layoutState.getMountableOutputAt(index);
  final long hostId=host.getId();
  for (int i=index + 1, size=layoutState.getMountableOutputCount(); i < size; i++) {
    final LayoutOutput layoutOutput=layoutState.getMountableOutputAt(i);
    long curentHostId=layoutOutput.getHostMarker();
    while (curentHostId != hostId) {
      if (curentHostId == ROOT_HOST_ID) {
        return i - 1;
      }
      final int parentIndex=layoutState.getLayoutOutputPositionForId(curentHostId);
      final LayoutOutput parent=layoutState.getMountableOutputAt(parentIndex);
      curentHostId=parent.getHostMarker();
    }
  }
  return layoutState.getMountableOutputCount() - 1;
}
