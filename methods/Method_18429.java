private void assertNoDanglingMountContent(MountItem item){
  final int index=mIndexToItemMap.indexOfValue(item);
  if (index > -1) {
    final long id=mIndexToItemMap.keyAt(index);
    int layoutOutputIndex=-1;
    for (int i=0; i < mLayoutOutputsIds.length; i++) {
      if (id == mLayoutOutputsIds[i]) {
        layoutOutputIndex=i;
        break;
      }
    }
    throw new RuntimeException("Got dangling mount content during animation: index=" + layoutOutputIndex + ", mapIndex=" + index + ", id=" + id + ", disappearRange=[" + mLastDisappearRangeStart + "," + mLastDisappearRangeEnd + "], contentType=" + item.getContent().getClass() + ", component=" + (item.getComponent() != null ? item.getComponent().getSimpleName() : null) + ", transitionId=" + item.getTransitionId() + ", host=" + item.getHost() + ", isRootHost=" + (mHostsByMarker.get(ROOT_HOST_ID) == item.getHost()));
  }
}
