public static int mix(int key,int seed){
  return mix32(key ^ seed);
}
