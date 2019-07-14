@Override protected @KeepCodecResult int canKeepCodec(MediaCodec codec,MediaCodecInfo codecInfo,Format oldFormat,Format newFormat){
  if (codecInfo.isSeamlessAdaptationSupported(oldFormat,newFormat,true) && newFormat.width <= codecMaxValues.width && newFormat.height <= codecMaxValues.height && getMaxInputSize(codecInfo,newFormat) <= codecMaxValues.inputSize) {
    return oldFormat.initializationDataEquals(newFormat) ? KEEP_CODEC_RESULT_YES_WITHOUT_RECONFIGURATION : KEEP_CODEC_RESULT_YES_WITH_RECONFIGURATION;
  }
  return KEEP_CODEC_RESULT_NO;
}
