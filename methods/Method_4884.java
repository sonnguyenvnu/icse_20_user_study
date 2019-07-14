/** 
 * @return Whether it may be possible to feed more input data.
 * @throws ExoPlaybackException If an error occurs feeding the input buffer.
 */
private boolean feedInputBuffer() throws ExoPlaybackException {
  if (codec == null || codecDrainState == DRAIN_STATE_WAIT_END_OF_STREAM || inputStreamEnded) {
    return false;
  }
  if (inputIndex < 0) {
    inputIndex=codec.dequeueInputBuffer(0);
    if (inputIndex < 0) {
      return false;
    }
    buffer.data=getInputBuffer(inputIndex);
    buffer.clear();
  }
  if (codecDrainState == DRAIN_STATE_SIGNAL_END_OF_STREAM) {
    if (codecNeedsEosPropagation) {
    }
 else {
      codecReceivedEos=true;
      codec.queueInputBuffer(inputIndex,0,0,0,MediaCodec.BUFFER_FLAG_END_OF_STREAM);
      resetInputBuffer();
    }
    codecDrainState=DRAIN_STATE_WAIT_END_OF_STREAM;
    return false;
  }
  if (codecNeedsAdaptationWorkaroundBuffer) {
    codecNeedsAdaptationWorkaroundBuffer=false;
    buffer.data.put(ADAPTATION_WORKAROUND_BUFFER);
    codec.queueInputBuffer(inputIndex,0,ADAPTATION_WORKAROUND_BUFFER.length,0,0);
    resetInputBuffer();
    codecReceivedBuffers=true;
    return true;
  }
  int result;
  int adaptiveReconfigurationBytes=0;
  if (waitingForKeys) {
    result=C.RESULT_BUFFER_READ;
  }
 else {
    if (codecReconfigurationState == RECONFIGURATION_STATE_WRITE_PENDING) {
      for (int i=0; i < codecFormat.initializationData.size(); i++) {
        byte[] data=codecFormat.initializationData.get(i);
        buffer.data.put(data);
      }
      codecReconfigurationState=RECONFIGURATION_STATE_QUEUE_PENDING;
    }
    adaptiveReconfigurationBytes=buffer.data.position();
    result=readSource(formatHolder,buffer,false);
  }
  if (result == C.RESULT_NOTHING_READ) {
    return false;
  }
  if (result == C.RESULT_FORMAT_READ) {
    if (codecReconfigurationState == RECONFIGURATION_STATE_QUEUE_PENDING) {
      buffer.clear();
      codecReconfigurationState=RECONFIGURATION_STATE_WRITE_PENDING;
    }
    onInputFormatChanged(formatHolder.format);
    return true;
  }
  if (buffer.isEndOfStream()) {
    if (codecReconfigurationState == RECONFIGURATION_STATE_QUEUE_PENDING) {
      buffer.clear();
      codecReconfigurationState=RECONFIGURATION_STATE_WRITE_PENDING;
    }
    inputStreamEnded=true;
    if (!codecReceivedBuffers) {
      processEndOfStream();
      return false;
    }
    try {
      if (codecNeedsEosPropagation) {
      }
 else {
        codecReceivedEos=true;
        codec.queueInputBuffer(inputIndex,0,0,0,MediaCodec.BUFFER_FLAG_END_OF_STREAM);
        resetInputBuffer();
      }
    }
 catch (    CryptoException e) {
      throw ExoPlaybackException.createForRenderer(e,getIndex());
    }
    return false;
  }
  if (waitingForFirstSyncSample && !buffer.isKeyFrame()) {
    buffer.clear();
    if (codecReconfigurationState == RECONFIGURATION_STATE_QUEUE_PENDING) {
      codecReconfigurationState=RECONFIGURATION_STATE_WRITE_PENDING;
    }
    return true;
  }
  waitingForFirstSyncSample=false;
  boolean bufferEncrypted=buffer.isEncrypted();
  waitingForKeys=shouldWaitForKeys(bufferEncrypted);
  if (waitingForKeys) {
    return false;
  }
  if (codecNeedsDiscardToSpsWorkaround && !bufferEncrypted) {
    NalUnitUtil.discardToSps(buffer.data);
    if (buffer.data.position() == 0) {
      return true;
    }
    codecNeedsDiscardToSpsWorkaround=false;
  }
  try {
    long presentationTimeUs=buffer.timeUs;
    if (buffer.isDecodeOnly()) {
      decodeOnlyPresentationTimestamps.add(presentationTimeUs);
    }
    if (waitingForFirstSampleInFormat) {
      formatQueue.add(presentationTimeUs,inputFormat);
      waitingForFirstSampleInFormat=false;
    }
    buffer.flip();
    onQueueInputBuffer(buffer);
    if (bufferEncrypted) {
      MediaCodec.CryptoInfo cryptoInfo=getFrameworkCryptoInfo(buffer,adaptiveReconfigurationBytes);
      codec.queueSecureInputBuffer(inputIndex,0,cryptoInfo,presentationTimeUs,0);
    }
 else {
      codec.queueInputBuffer(inputIndex,0,buffer.data.limit(),presentationTimeUs,0);
    }
    resetInputBuffer();
    codecReceivedBuffers=true;
    codecReconfigurationState=RECONFIGURATION_STATE_NONE;
    decoderCounters.inputBufferCount++;
  }
 catch (  CryptoException e) {
    throw ExoPlaybackException.createForRenderer(e,getIndex());
  }
  return true;
}
