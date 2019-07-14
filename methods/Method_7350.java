private static long bytesToLong(byte[] arr,int offset){
  return (((long)arr[offset] & 0x7F) << 56) | (((long)arr[offset + 1] & 0xFF) << 48) | (((long)arr[offset + 2] & 0xFF) << 40) | (((long)arr[offset + 3] & 0xFF) << 32) | (((long)arr[offset + 4] & 0xFF) << 24) | (((long)arr[offset + 5] & 0xFF) << 16) | (((long)arr[offset + 6] & 0xFF) << 8) | (((long)arr[offset + 7] & 0xFF));
}
