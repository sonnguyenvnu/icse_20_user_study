private void maybeNotifyDownstreamFormat(int track){
  PreparedState preparedState=getPreparedState();
  boolean[] trackNotifiedDownstreamFormats=preparedState.trackNotifiedDownstreamFormats;
  if (!trackNotifiedDownstreamFormats[track]) {
    Format trackFormat=preparedState.tracks.get(track).getFormat(0);
    eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(trackFormat.sampleMimeType),trackFormat,C.SELECTION_REASON_UNKNOWN,null,lastSeekPositionUs);
    trackNotifiedDownstreamFormats[track]=true;
  }
}
