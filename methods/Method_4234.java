@Override protected void onInputFormatChanged(Format newFormat) throws ExoPlaybackException {
  super.onInputFormatChanged(newFormat);
  eventDispatcher.inputFormatChanged(newFormat);
  pcmEncoding=MimeTypes.AUDIO_RAW.equals(newFormat.sampleMimeType) ? newFormat.pcmEncoding : C.ENCODING_PCM_16BIT;
  channelCount=newFormat.channelCount;
  encoderDelay=newFormat.encoderDelay;
  encoderPadding=newFormat.encoderPadding;
}
