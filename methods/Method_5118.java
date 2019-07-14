@Override protected void onChildSourceInfoRefreshed(MediaPeriodId mediaPeriodId,MediaSource mediaSource,Timeline timeline,@Nullable Object manifest){
  if (mediaPeriodId.isAd()) {
    int adGroupIndex=mediaPeriodId.adGroupIndex;
    int adIndexInAdGroup=mediaPeriodId.adIndexInAdGroup;
    onAdSourceInfoRefreshed(mediaSource,adGroupIndex,adIndexInAdGroup,timeline);
  }
 else {
    onContentSourceInfoRefreshed(timeline,manifest);
  }
}
