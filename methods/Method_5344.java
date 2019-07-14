private long resolveTimeToLiveEdgeUs(long playbackPositionUs){
  final boolean resolveTimeToLiveEdgePossible=liveEdgeInPeriodTimeUs != C.TIME_UNSET;
  return resolveTimeToLiveEdgePossible ? liveEdgeInPeriodTimeUs - playbackPositionUs : C.TIME_UNSET;
}
