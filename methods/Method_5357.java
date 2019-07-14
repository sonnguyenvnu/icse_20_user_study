@Override public boolean continueLoading(long positionUs){
  if (trackGroups == null) {
    for (    HlsSampleStreamWrapper wrapper : sampleStreamWrappers) {
      wrapper.continuePreparing();
    }
    return false;
  }
 else {
    return compositeSequenceableLoader.continueLoading(positionUs);
  }
}
