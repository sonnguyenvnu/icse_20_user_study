private static int finalize(int key){
  key+=key << 3;
  key^=key >>> 11;
  key+=key << 15;
  return key;
}
