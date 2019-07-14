@Override public Format getOutputFormat(){
  Assertions.checkNotNull(decoder);
  int channelCount=decoder.getChannelCount();
  int sampleRate=decoder.getSampleRate();
  @C.PcmEncoding int encoding=decoder.getEncoding();
  return Format.createAudioSampleFormat(null,MimeTypes.AUDIO_RAW,null,Format.NO_VALUE,Format.NO_VALUE,channelCount,sampleRate,encoding,Collections.emptyList(),null,0,null);
}
