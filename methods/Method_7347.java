public void init(int sampleRate,int bitsPerSample,int channels,int bufferSize){
  if (audioTrack != null) {
    throw new IllegalStateException("already inited");
  }
  int size=getBufferSize(bufferSize,48000);
  this.bufferSize=bufferSize;
  audioTrack=new AudioTrack(AudioManager.STREAM_VOICE_CALL,48000,channels == 1 ? AudioFormat.CHANNEL_OUT_MONO : AudioFormat.CHANNEL_OUT_STEREO,AudioFormat.ENCODING_PCM_16BIT,size,AudioTrack.MODE_STREAM);
  if (audioTrack.getState() != AudioTrack.STATE_INITIALIZED) {
    VLog.w("Error initializing AudioTrack with 48k, trying 44.1k with resampling");
    try {
      audioTrack.release();
    }
 catch (    Throwable ignore) {
    }
    size=getBufferSize(bufferSize * 6,44100);
    VLog.d("buffer size: " + size);
    audioTrack=new AudioTrack(AudioManager.STREAM_VOICE_CALL,44100,channels == 1 ? AudioFormat.CHANNEL_OUT_MONO : AudioFormat.CHANNEL_OUT_STEREO,AudioFormat.ENCODING_PCM_16BIT,size,AudioTrack.MODE_STREAM);
    needResampling=true;
  }
}
