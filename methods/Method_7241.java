public static int idealIntArraySize(int need){
  return idealByteArraySize(need * 4) / 4;
}
