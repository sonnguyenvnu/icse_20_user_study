private void onAdPlaybackState(AdPlaybackState adPlaybackState){
  if (this.adPlaybackState == null) {
    adGroupMediaSources=new MediaSource[adPlaybackState.adGroupCount][];
    Arrays.fill(adGroupMediaSources,new MediaSource[0]);
    adGroupTimelines=new Timeline[adPlaybackState.adGroupCount][];
    Arrays.fill(adGroupTimelines,new Timeline[0]);
  }
  this.adPlaybackState=adPlaybackState;
  maybeUpdateSourceInfo();
}
