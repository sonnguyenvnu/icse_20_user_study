private void next(){
  mIndex++;
  mIndex%=ORIENTATIONS.length;
  mLabel="To " + ORIENTATIONS[mIndex] + " " + (CLOCKWISES[mIndex] == null ? "" : CLOCKWISES[mIndex] ? "clockwise" : "anticlockwise");
  btnCache.setText(mLabel);
}
