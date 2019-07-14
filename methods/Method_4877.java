private void getCodecBuffers(MediaCodec codec){
  if (Util.SDK_INT < 21) {
    inputBuffers=codec.getInputBuffers();
    outputBuffers=codec.getOutputBuffers();
  }
}
