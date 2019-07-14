private static int getLimit(long limitAndValuePos){
  return (int)(limitAndValuePos >>> 32L);
}
