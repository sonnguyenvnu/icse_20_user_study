private void resetCueBuilders(){
  currentCueBuilder.reset(captionMode);
  cueBuilders.clear();
  cueBuilders.add(currentCueBuilder);
}
