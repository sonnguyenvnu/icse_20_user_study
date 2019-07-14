public static int getCRC16(String key){
  byte[] bytesKey=SafeEncoder.encode(key);
  return getCRC16(bytesKey,0,bytesKey.length);
}
