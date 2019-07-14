@Override public void onDragBottom(boolean rightToLeft){
  if (rightToLeft) {
    saveAndExit();
    return;
  }
  finish();
}
