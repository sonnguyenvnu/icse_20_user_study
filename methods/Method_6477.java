private int findNextPreloadDownloadOffset(int atomOffset,int partOffset,NativeByteBuffer partBuffer){
  int partSize=partBuffer.limit();
  while (true) {
    if (atomOffset < partOffset - (preloadTempBuffer != null ? 16 : 0) || atomOffset >= partOffset + partSize) {
      return 0;
    }
    if (atomOffset >= partOffset + partSize - 16) {
      preloadTempBufferCount=partOffset + partSize - atomOffset;
      partBuffer.position(partBuffer.limit() - preloadTempBufferCount);
      partBuffer.readBytes(preloadTempBuffer,0,preloadTempBufferCount,false);
      return partOffset + partSize;
    }
    if (preloadTempBufferCount != 0) {
      partBuffer.position(0);
      partBuffer.readBytes(preloadTempBuffer,preloadTempBufferCount,16 - preloadTempBufferCount,false);
      preloadTempBufferCount=0;
    }
 else {
      partBuffer.position(atomOffset - partOffset);
      partBuffer.readBytes(preloadTempBuffer,0,16,false);
    }
    int atomSize=(((int)preloadTempBuffer[0] & 0xFF) << 24) + (((int)preloadTempBuffer[1] & 0xFF) << 16) + (((int)preloadTempBuffer[2] & 0xFF) << 8) + ((int)preloadTempBuffer[3] & 0xFF);
    if (atomSize == 0) {
      return 0;
    }
 else     if (atomSize == 1) {
      atomSize=(((int)preloadTempBuffer[12] & 0xFF) << 24) + (((int)preloadTempBuffer[13] & 0xFF) << 16) + (((int)preloadTempBuffer[14] & 0xFF) << 8) + ((int)preloadTempBuffer[15] & 0xFF);
    }
    if (preloadTempBuffer[4] == 'm' && preloadTempBuffer[5] == 'o' && preloadTempBuffer[6] == 'o' && preloadTempBuffer[7] == 'v') {
      return -atomSize;
    }
    if (atomSize + atomOffset >= partOffset + partSize) {
      return atomSize + atomOffset;
    }
    atomOffset+=atomSize;
  }
}
