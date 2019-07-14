private void resetCodecBuffers(){
  if (Util.SDK_INT < 21) {
    inputBuffers=null;
    outputBuffers=null;
  }
}
