public int getCurrentProgressMs(){
  if (pendingSeekToUI >= 0) {
    return (int)pendingSeekToUI;
  }
  return nextRenderingBitmapTime != 0 ? nextRenderingBitmapTime : renderingBitmapTime;
}
