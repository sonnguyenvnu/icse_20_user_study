public void seekTo(long ms,boolean removeLoading){
synchronized (sync) {
    pendingSeekTo=ms;
    pendingSeekToUI=ms;
    prepareToSeek(nativePtr);
    if (decoderCreated && stream != null) {
      stream.cancel(removeLoading);
      pendingRemoveLoading=removeLoading;
      pendingRemoveLoadingFramesReset=pendingRemoveLoading ? 0 : 10;
    }
  }
}
