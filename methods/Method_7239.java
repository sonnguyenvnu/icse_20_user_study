public static int idealShortArraySize(int need){
  return idealByteArraySize(need * 2) / 2;
}
