@Override public QualityInfo getQualityInfo(int scanNumber){
  return ImmutableQualityInfo.of(scanNumber,scanNumber >= mDynamicValueConfig.getGoodEnoughScanNumber(),false);
}
