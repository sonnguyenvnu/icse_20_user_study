private void removeChangeCurrentByOneFromLongPress(){
  if (mChangeCurrentByOneFromLongPressCommand != null) {
    removeCallbacks(mChangeCurrentByOneFromLongPressCommand);
  }
}
