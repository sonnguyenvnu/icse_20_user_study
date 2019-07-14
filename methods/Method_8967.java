@Override public void onSeekBarDrag(float progress){
  if (currentMessageObject == null) {
    return;
  }
  currentMessageObject.audioProgress=progress;
  MediaController.getInstance().seekToProgress(currentMessageObject,progress);
}
