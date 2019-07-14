private static int bytesToInt(byte[] arr,int offset){
  return (((int)arr[offset] & 0x7F) << 24) | (((int)arr[offset + 1] & 0xFF) << 16) | (((int)arr[offset + 2] & 0xFF) << 8) | ((int)arr[offset + 3] & 0xFF);
}
