public static int mix(Object key,int seed){
  return key == null ? 0 : mix32(key.hashCode() ^ seed);
}
