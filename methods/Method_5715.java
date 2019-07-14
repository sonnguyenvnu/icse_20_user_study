private static int getClosestBitrateIndex(int formatBitrate,int[] formatBitrates){
  int closestDistance=Integer.MAX_VALUE;
  int closestFormat=C.INDEX_UNSET;
  for (int j=0; j < formatBitrates.length; j++) {
    if (formatBitrates[j] != Format.NO_VALUE) {
      int distance=Math.abs(formatBitrates[j] - formatBitrate);
      if (distance < closestDistance) {
        closestDistance=distance;
        closestFormat=j;
      }
    }
  }
  return closestFormat;
}
