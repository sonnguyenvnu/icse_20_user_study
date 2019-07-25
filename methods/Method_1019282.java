public static int mix(short key,int seed){
  return mixPhi(key ^ seed);
}
