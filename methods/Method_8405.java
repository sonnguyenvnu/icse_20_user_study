@Override public int getMinimumHeight(){
  int height=decoderCreated ? (metaData[2] == 90 || metaData[2] == 270 ? metaData[0] : metaData[1]) : 0;
  if (height == 0) {
    return AndroidUtilities.dp(100);
  }
  return height;
}
