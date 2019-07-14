@Override protected void onStreamChanged(Format[] formats,long offsetUs) throws ExoPlaybackException {
  if (outputStreamOffsetUs == C.TIME_UNSET) {
    outputStreamOffsetUs=offsetUs;
  }
 else {
    if (pendingOutputStreamOffsetCount == pendingOutputStreamOffsetsUs.length) {
      Log.w(TAG,"Too many stream changes, so dropping offset: " + pendingOutputStreamOffsetsUs[pendingOutputStreamOffsetCount - 1]);
    }
 else {
      pendingOutputStreamOffsetCount++;
    }
    pendingOutputStreamOffsetsUs[pendingOutputStreamOffsetCount - 1]=offsetUs;
    pendingOutputStreamSwitchTimesUs[pendingOutputStreamOffsetCount - 1]=lastInputTimeUs;
  }
  super.onStreamChanged(formats,offsetUs);
}
