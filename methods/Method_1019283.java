public static int mix(char key,int seed){
  return mixPhi(key ^ seed);
}
