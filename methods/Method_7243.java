public static int idealObjectArraySize(int need){
  return idealByteArraySize(need * 4) / 4;
}
