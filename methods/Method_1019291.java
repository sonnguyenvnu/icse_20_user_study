public static int mix(long key,int seed){
  return (int)mix64(key ^ seed);
}
