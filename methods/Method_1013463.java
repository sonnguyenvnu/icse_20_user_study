public static IntValue gen(int i){
  if (i >= 0 && i < cache.length) {
    return cache[i];
  }
  return new IntValue(i);
}
