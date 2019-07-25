private static boolean overlap(long min0,long max0,long min1,long max1){
  return (max1 - min0) >= 0 && (max0 - min1) >= 0;
}
