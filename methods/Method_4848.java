public Format copyWithMaxInputSize(int maxInputSize){
  return new Format(id,label,containerMimeType,sampleMimeType,codecs,bitrate,maxInputSize,width,height,frameRate,rotationDegrees,pixelWidthHeightRatio,projectionData,stereoMode,colorInfo,channelCount,sampleRate,pcmEncoding,encoderDelay,encoderPadding,selectionFlags,language,accessibilityChannel,subsampleOffsetUs,initializationData,drmInitData,metadata);
}
