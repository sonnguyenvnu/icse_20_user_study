private long resolveTimeToLiveEdgeUs(long playbackPositionUs){
  boolean resolveTimeToLiveEdgePossible=manifest.dynamic && liveEdgeTimeUs != C.TIME_UNSET;
  return resolveTimeToLiveEdgePossible ? liveEdgeTimeUs - playbackPositionUs : C.TIME_UNSET;
}
