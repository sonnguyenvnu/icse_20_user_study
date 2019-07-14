private void maybeInitExtraTracks(){
  if (emsgTrackOutputs == null) {
    emsgTrackOutputs=new TrackOutput[2];
    int emsgTrackOutputCount=0;
    if (additionalEmsgTrackOutput != null) {
      emsgTrackOutputs[emsgTrackOutputCount++]=additionalEmsgTrackOutput;
    }
    if ((flags & FLAG_ENABLE_EMSG_TRACK) != 0) {
      emsgTrackOutputs[emsgTrackOutputCount++]=extractorOutput.track(trackBundles.size(),C.TRACK_TYPE_METADATA);
    }
    emsgTrackOutputs=Arrays.copyOf(emsgTrackOutputs,emsgTrackOutputCount);
    for (    TrackOutput eventMessageTrackOutput : emsgTrackOutputs) {
      eventMessageTrackOutput.format(EMSG_FORMAT);
    }
  }
  if (cea608TrackOutputs == null) {
    cea608TrackOutputs=new TrackOutput[closedCaptionFormats.size()];
    for (int i=0; i < cea608TrackOutputs.length; i++) {
      TrackOutput output=extractorOutput.track(trackBundles.size() + 1 + i,C.TRACK_TYPE_TEXT);
      output.format(closedCaptionFormats.get(i));
      cea608TrackOutputs[i]=output;
    }
  }
}
