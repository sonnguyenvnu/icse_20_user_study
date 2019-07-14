private void updateLiveEdgeTimeUs(HlsMediaPlaylist mediaPlaylist){
  liveEdgeInPeriodTimeUs=mediaPlaylist.hasEndTag ? C.TIME_UNSET : (mediaPlaylist.getEndTimeUs() - playlistTracker.getInitialStartTimeUs());
}
