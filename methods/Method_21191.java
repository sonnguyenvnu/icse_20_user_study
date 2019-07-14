private void preparePlayer(final @NonNull String videoUrl){
  final TrackSelection.Factory adaptiveTrackSelectionFactory=new AdaptiveTrackSelection.Factory();
  this.trackSelector=new DefaultTrackSelector(adaptiveTrackSelectionFactory);
  this.player=ExoPlayerFactory.newSimpleInstance(this,this.trackSelector);
  this.playerView.setPlayer(this.player);
  this.player.addListener(this.eventListener);
  this.player.seekTo(this.playerPosition);
  final boolean playerIsResuming=this.playerPosition != 0;
  this.player.prepare(getMediaSource(videoUrl),playerIsResuming,false);
  this.player.setPlayWhenReady(true);
}
