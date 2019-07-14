public static long makeBroadcastId(int id){
  return 0x0000000100000000L | ((long)id & 0x00000000FFFFFFFFL);
}
