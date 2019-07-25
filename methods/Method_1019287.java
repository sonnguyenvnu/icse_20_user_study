public static int mix(float key,int seed){
  return mix32(Float.floatToIntBits(key) ^ seed);
}
