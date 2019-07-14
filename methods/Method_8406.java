@Override public int getMinimumWidth(){
  int width=decoderCreated ? (metaData[2] == 90 || metaData[2] == 270 ? metaData[1] : metaData[0]) : 0;
  if (width == 0) {
    return AndroidUtilities.dp(100);
  }
  return width;
}
