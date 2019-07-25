public static int mix(byte key,int seed){
  return (key ^ seed) * PHI_C32;
}
