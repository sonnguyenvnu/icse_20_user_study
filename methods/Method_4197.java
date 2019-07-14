@Override @SuppressWarnings("ReferenceEquality") public boolean handleBuffer(ByteBuffer buffer,long presentationTimeUs) throws InitializationException, WriteException {
  Assertions.checkArgument(inputBuffer == null || buffer == inputBuffer);
  if (!isInitialized()) {
    initialize();
    if (playing) {
      play();
    }
  }
  if (!audioTrackPositionTracker.mayHandleBuffer(getWrittenFrames())) {
    return false;
  }
  if (inputBuffer == null) {
    if (!buffer.hasRemaining()) {
      return true;
    }
    if (!isInputPcm && framesPerEncodedSample == 0) {
      framesPerEncodedSample=getFramesPerEncodedSample(outputEncoding,buffer);
      if (framesPerEncodedSample == 0) {
        return true;
      }
    }
    if (afterDrainPlaybackParameters != null) {
      if (!drainAudioProcessorsToEndOfStream()) {
        return false;
      }
      PlaybackParameters newPlaybackParameters=afterDrainPlaybackParameters;
      afterDrainPlaybackParameters=null;
      newPlaybackParameters=audioProcessorChain.applyPlaybackParameters(newPlaybackParameters);
      playbackParametersCheckpoints.add(new PlaybackParametersCheckpoint(newPlaybackParameters,Math.max(0,presentationTimeUs),framesToDurationUs(getWrittenFrames())));
      setupAudioProcessors();
    }
    if (startMediaTimeState == START_NOT_SET) {
      startMediaTimeUs=Math.max(0,presentationTimeUs);
      startMediaTimeState=START_IN_SYNC;
    }
 else {
      long expectedPresentationTimeUs=startMediaTimeUs + inputFramesToDurationUs(getSubmittedFrames() - trimmingAudioProcessor.getTrimmedFrameCount());
      if (startMediaTimeState == START_IN_SYNC && Math.abs(expectedPresentationTimeUs - presentationTimeUs) > 200000) {
        Log.e(TAG,"Discontinuity detected [expected " + expectedPresentationTimeUs + ", got " + presentationTimeUs + "]");
        startMediaTimeState=START_NEED_SYNC;
      }
      if (startMediaTimeState == START_NEED_SYNC) {
        long adjustmentUs=presentationTimeUs - expectedPresentationTimeUs;
        startMediaTimeUs+=adjustmentUs;
        startMediaTimeState=START_IN_SYNC;
        if (listener != null && adjustmentUs != 0) {
          listener.onPositionDiscontinuity();
        }
      }
    }
    if (isInputPcm) {
      submittedPcmBytes+=buffer.remaining();
    }
 else {
      submittedEncodedFrames+=framesPerEncodedSample;
    }
    inputBuffer=buffer;
  }
  if (processingEnabled) {
    processBuffers(presentationTimeUs);
  }
 else {
    writeBuffer(inputBuffer,presentationTimeUs);
  }
  if (!inputBuffer.hasRemaining()) {
    inputBuffer=null;
    return true;
  }
  if (audioTrackPositionTracker.isStalled(getWrittenFrames())) {
    Log.w(TAG,"Resetting stalled audio track");
    flush();
    return true;
  }
  return false;
}
