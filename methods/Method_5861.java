@Override public void onTracksChanged(EventTime eventTime,TrackGroupArray ignored,TrackSelectionArray trackSelections){
  MappedTrackInfo mappedTrackInfo=trackSelector != null ? trackSelector.getCurrentMappedTrackInfo() : null;
  if (mappedTrackInfo == null) {
    logd(eventTime,"tracksChanged","[]");
    return;
  }
  logd("tracksChanged [" + getEventTimeString(eventTime) + ", ");
  int rendererCount=mappedTrackInfo.getRendererCount();
  for (int rendererIndex=0; rendererIndex < rendererCount; rendererIndex++) {
    TrackGroupArray rendererTrackGroups=mappedTrackInfo.getTrackGroups(rendererIndex);
    TrackSelection trackSelection=trackSelections.get(rendererIndex);
    if (rendererTrackGroups.length > 0) {
      logd("  Renderer:" + rendererIndex + " [");
      for (int groupIndex=0; groupIndex < rendererTrackGroups.length; groupIndex++) {
        TrackGroup trackGroup=rendererTrackGroups.get(groupIndex);
        String adaptiveSupport=getAdaptiveSupportString(trackGroup.length,mappedTrackInfo.getAdaptiveSupport(rendererIndex,groupIndex,false));
        logd("    Group:" + groupIndex + ", adaptive_supported=" + adaptiveSupport + " [");
        for (int trackIndex=0; trackIndex < trackGroup.length; trackIndex++) {
          String status=getTrackStatusString(trackSelection,trackGroup,trackIndex);
          String formatSupport=getFormatSupportString(mappedTrackInfo.getTrackSupport(rendererIndex,groupIndex,trackIndex));
          logd("      " + status + " Track:" + trackIndex + ", " + Format.toLogString(trackGroup.getFormat(trackIndex)) + ", supported=" + formatSupport);
        }
        logd("    ]");
      }
      if (trackSelection != null) {
        for (int selectionIndex=0; selectionIndex < trackSelection.length(); selectionIndex++) {
          Metadata metadata=trackSelection.getFormat(selectionIndex).metadata;
          if (metadata != null) {
            logd("    Metadata [");
            printMetadata(metadata,"      ");
            logd("    ]");
            break;
          }
        }
      }
      logd("  ]");
    }
  }
  TrackGroupArray unassociatedTrackGroups=mappedTrackInfo.getUnmappedTrackGroups();
  if (unassociatedTrackGroups.length > 0) {
    logd("  Renderer:None [");
    for (int groupIndex=0; groupIndex < unassociatedTrackGroups.length; groupIndex++) {
      logd("    Group:" + groupIndex + " [");
      TrackGroup trackGroup=unassociatedTrackGroups.get(groupIndex);
      for (int trackIndex=0; trackIndex < trackGroup.length; trackIndex++) {
        String status=getTrackStatusString(false);
        String formatSupport=getFormatSupportString(RendererCapabilities.FORMAT_UNSUPPORTED_TYPE);
        logd("      " + status + " Track:" + trackIndex + ", " + Format.toLogString(trackGroup.getFormat(trackIndex)) + ", supported=" + formatSupport);
      }
      logd("    ]");
    }
    logd("  ]");
  }
  logd("]");
}
