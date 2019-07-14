public float getScale(int boundsWidth,int boundsHeight){
  int offset=AndroidUtilities.dp(16);
  return Math.max(((float)boundsWidth + offset * 2) / (float)boundsWidth,((float)boundsHeight + offset * 2) / (float)boundsHeight);
}
