private boolean tryInit(int source,int sampleRate){
  if (audioRecord != null) {
    try {
      audioRecord.release();
    }
 catch (    Exception ignore) {
    }
  }
  VLog.i("Trying to initialize AudioRecord with source=" + source + " and sample rate=" + sampleRate);
  int size=getBufferSize(bufferSize,48000);
  try {
    audioRecord=new AudioRecord(source,sampleRate,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT,size);
  }
 catch (  Exception x) {
    VLog.e("AudioRecord init failed!",x);
  }
  needResampling=sampleRate != 48000;
  return audioRecord != null && audioRecord.getState() == AudioRecord.STATE_INITIALIZED;
}
