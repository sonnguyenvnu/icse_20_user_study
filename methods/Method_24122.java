@Override protected boolean textModeCheck(int mode){
  return mode == MODEL || (mode == SHAPE && PGL.SHAPE_TEXT_SUPPORTED);
}
