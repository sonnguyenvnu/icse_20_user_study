private int getBufferSize(int min,int sampleRate){
  return Math.max(AudioTrack.getMinBufferSize(sampleRate,AudioFormat.CHANNEL_OUT_MONO,AudioFormat.ENCODING_PCM_16BIT),min);
}
