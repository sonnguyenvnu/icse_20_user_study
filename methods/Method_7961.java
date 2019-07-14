public void setHighlighted(boolean value){
  if (isHighlighted == value) {
    return;
  }
  isHighlighted=value;
  if (!isHighlighted) {
    lastHighlightProgressTime=System.currentTimeMillis();
    isHighlightedAnimated=true;
    highlightProgress=300;
  }
 else {
    isHighlightedAnimated=false;
    highlightProgress=0;
  }
  updateRadialProgressBackground();
  if (useSeekBarWaweform) {
    seekBarWaveform.setSelected(isDrawSelectionBackground());
  }
 else {
    seekBar.setSelected(isDrawSelectionBackground());
  }
  invalidate();
  if (getParent() != null) {
    ((View)getParent()).invalidate();
  }
}
