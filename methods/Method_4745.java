private boolean isAdtsSyncBytes(byte firstByte,byte secondByte){
  int syncWord=(firstByte & 0xFF) << 8 | (secondByte & 0xFF);
  return isAdtsSyncWord(syncWord);
}
