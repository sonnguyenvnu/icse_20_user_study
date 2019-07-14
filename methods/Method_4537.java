private void maybeOutputFormat(){
  if (!hasOutputFormat) {
    hasOutputFormat=true;
    String mimeType=isWideBand ? MimeTypes.AUDIO_AMR_WB : MimeTypes.AUDIO_AMR_NB;
    int sampleRate=isWideBand ? SAMPLE_RATE_WB : SAMPLE_RATE_NB;
    trackOutput.format(Format.createAudioSampleFormat(null,mimeType,null,Format.NO_VALUE,MAX_FRAME_SIZE_BYTES,1,sampleRate,Format.NO_VALUE,null,null,0,null));
  }
}
