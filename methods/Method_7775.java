public void setPulledUp(){
  if (pulledUp) {
    return;
  }
  pulledUp=true;
  AndroidUtilities.runOnUIThread(() -> notifyItemChanged(liveLocationType == 0 ? 2 : 3));
}
