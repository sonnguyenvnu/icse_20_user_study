@Override public void onPrepared(MediaPeriod preparedPeriod){
  childrenPendingPreparation.remove(preparedPeriod);
  if (!childrenPendingPreparation.isEmpty()) {
    return;
  }
  int totalTrackGroupCount=0;
  for (  MediaPeriod period : periods) {
    totalTrackGroupCount+=period.getTrackGroups().length;
  }
  TrackGroup[] trackGroupArray=new TrackGroup[totalTrackGroupCount];
  int trackGroupIndex=0;
  for (  MediaPeriod period : periods) {
    TrackGroupArray periodTrackGroups=period.getTrackGroups();
    int periodTrackGroupCount=periodTrackGroups.length;
    for (int j=0; j < periodTrackGroupCount; j++) {
      trackGroupArray[trackGroupIndex++]=periodTrackGroups.get(j);
    }
  }
  trackGroups=new TrackGroupArray(trackGroupArray);
  callback.onPrepared(this);
}
