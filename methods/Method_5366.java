@Override public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist playlist){
  SinglePeriodTimeline timeline;
  long windowStartTimeMs=playlist.hasProgramDateTime ? C.usToMs(playlist.startTimeUs) : C.TIME_UNSET;
  long presentationStartTimeMs=playlist.playlistType == HlsMediaPlaylist.PLAYLIST_TYPE_EVENT || playlist.playlistType == HlsMediaPlaylist.PLAYLIST_TYPE_VOD ? windowStartTimeMs : C.TIME_UNSET;
  long windowDefaultStartPositionUs=playlist.startOffsetUs;
  if (playlistTracker.isLive()) {
    long offsetFromInitialStartTimeUs=playlist.startTimeUs - playlistTracker.getInitialStartTimeUs();
    long periodDurationUs=playlist.hasEndTag ? offsetFromInitialStartTimeUs + playlist.durationUs : C.TIME_UNSET;
    List<HlsMediaPlaylist.Segment> segments=playlist.segments;
    if (windowDefaultStartPositionUs == C.TIME_UNSET) {
      windowDefaultStartPositionUs=segments.isEmpty() ? 0 : segments.get(Math.max(0,segments.size() - 3)).relativeStartTimeUs;
    }
    timeline=new SinglePeriodTimeline(presentationStartTimeMs,windowStartTimeMs,periodDurationUs,playlist.durationUs,offsetFromInitialStartTimeUs,windowDefaultStartPositionUs,true,!playlist.hasEndTag,tag);
  }
 else {
    if (windowDefaultStartPositionUs == C.TIME_UNSET) {
      windowDefaultStartPositionUs=0;
    }
    timeline=new SinglePeriodTimeline(presentationStartTimeMs,windowStartTimeMs,playlist.durationUs,playlist.durationUs,0,windowDefaultStartPositionUs,true,false,tag);
  }
  refreshSourceInfo(timeline,new HlsManifest(playlistTracker.getMasterPlaylist(),playlist));
}
