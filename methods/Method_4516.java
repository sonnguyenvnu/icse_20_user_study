@Override public void seek(long position,long timeUs){
  if (position == 0) {
    readPastStreamInfo=false;
  }
  if (decoderJni != null) {
    decoderJni.reset(position);
  }
  if (flacBinarySearchSeeker != null) {
    flacBinarySearchSeeker.setSeekTargetUs(timeUs);
  }
}
