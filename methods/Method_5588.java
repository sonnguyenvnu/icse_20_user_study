private void releaseBuffers(){
  nextInputBuffer=null;
  nextSubtitleEventIndex=C.INDEX_UNSET;
  if (subtitle != null) {
    subtitle.release();
    subtitle=null;
  }
  if (nextSubtitle != null) {
    nextSubtitle.release();
    nextSubtitle=null;
  }
}
