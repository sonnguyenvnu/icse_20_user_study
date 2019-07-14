@Override public void onDragBottom(boolean rightToLeft){
  if (rightToLeft) {
    if (showSearch) {
      fragment.onDragBottom(rightToLeft);
    }
    return;
  }
  finish();
}
