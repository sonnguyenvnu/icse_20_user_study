public static int idealFloatArraySize(int need){
  return idealByteArraySize(need * 4) / 4;
}
