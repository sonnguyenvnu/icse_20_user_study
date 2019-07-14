private void initCodec(MediaCodecInfo codecInfo,MediaCrypto crypto) throws Exception {
  long codecInitializingTimestamp;
  long codecInitializedTimestamp;
  MediaCodec codec=null;
  String codecName=codecInfo.name;
  float codecOperatingRate=Util.SDK_INT < 23 ? CODEC_OPERATING_RATE_UNSET : getCodecOperatingRateV23(rendererOperatingRate,inputFormat,getStreamFormats());
  if (codecOperatingRate <= assumedMinimumCodecOperatingRate) {
    codecOperatingRate=CODEC_OPERATING_RATE_UNSET;
  }
  try {
    codecInitializingTimestamp=SystemClock.elapsedRealtime();
    TraceUtil.beginSection("createCodec:" + codecName);
    codec=MediaCodec.createByCodecName(codecName);
    TraceUtil.endSection();
    TraceUtil.beginSection("configureCodec");
    configureCodec(codecInfo,codec,inputFormat,crypto,codecOperatingRate);
    TraceUtil.endSection();
    TraceUtil.beginSection("startCodec");
    codec.start();
    TraceUtil.endSection();
    codecInitializedTimestamp=SystemClock.elapsedRealtime();
    getCodecBuffers(codec);
  }
 catch (  Exception e) {
    if (codec != null) {
      resetCodecBuffers();
      codec.release();
    }
    throw e;
  }
  this.codec=codec;
  this.codecInfo=codecInfo;
  this.codecOperatingRate=codecOperatingRate;
  codecFormat=inputFormat;
  codecAdaptationWorkaroundMode=codecAdaptationWorkaroundMode(codecName);
  codecNeedsReconfigureWorkaround=codecNeedsReconfigureWorkaround(codecName);
  codecNeedsDiscardToSpsWorkaround=codecNeedsDiscardToSpsWorkaround(codecName,codecFormat);
  codecNeedsFlushWorkaround=codecNeedsFlushWorkaround(codecName);
  codecNeedsEosFlushWorkaround=codecNeedsEosFlushWorkaround(codecName);
  codecNeedsEosOutputExceptionWorkaround=codecNeedsEosOutputExceptionWorkaround(codecName);
  codecNeedsMonoChannelCountWorkaround=codecNeedsMonoChannelCountWorkaround(codecName,codecFormat);
  codecNeedsEosPropagation=codecNeedsEosPropagationWorkaround(codecInfo) || getCodecNeedsEosPropagation();
  resetInputBuffer();
  resetOutputBuffer();
  codecHotswapDeadlineMs=getState() == STATE_STARTED ? (SystemClock.elapsedRealtime() + MAX_CODEC_HOTSWAP_TIME_MS) : C.TIME_UNSET;
  codecReconfigured=false;
  codecReconfigurationState=RECONFIGURATION_STATE_NONE;
  codecReceivedEos=false;
  codecReceivedBuffers=false;
  codecDrainState=DRAIN_STATE_NONE;
  codecDrainAction=DRAIN_ACTION_NONE;
  codecNeedsAdaptationWorkaroundBuffer=false;
  shouldSkipAdaptationWorkaroundOutputBuffer=false;
  shouldSkipOutputBuffer=false;
  waitingForFirstSyncSample=true;
  decoderCounters.decoderInitCount++;
  long elapsed=codecInitializedTimestamp - codecInitializingTimestamp;
  onCodecInitialized(codecName,codecInitializedTimestamp,elapsed);
}
