private int getBufferSize(int min,int sampleRate){
  return Math.max(AudioRecord.getMinBufferSize(sampleRate,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT),min);
}
