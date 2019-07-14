@TargetApi(21) private int writeNonBlockingWithAvSyncV21(AudioTrack audioTrack,ByteBuffer buffer,int size,long presentationTimeUs){
  if (avSyncHeader == null) {
    avSyncHeader=ByteBuffer.allocate(16);
    avSyncHeader.order(ByteOrder.BIG_ENDIAN);
    avSyncHeader.putInt(0x55550001);
  }
  if (bytesUntilNextAvSync == 0) {
    avSyncHeader.putInt(4,size);
    avSyncHeader.putLong(8,presentationTimeUs * 1000);
    avSyncHeader.position(0);
    bytesUntilNextAvSync=size;
  }
  int avSyncHeaderBytesRemaining=avSyncHeader.remaining();
  if (avSyncHeaderBytesRemaining > 0) {
    int result=audioTrack.write(avSyncHeader,avSyncHeaderBytesRemaining,WRITE_NON_BLOCKING);
    if (result < 0) {
      bytesUntilNextAvSync=0;
      return result;
    }
    if (result < avSyncHeaderBytesRemaining) {
      return 0;
    }
  }
  int result=writeNonBlockingV21(audioTrack,buffer,size);
  if (result < 0) {
    bytesUntilNextAvSync=0;
    return result;
  }
  bytesUntilNextAvSync-=result;
  return result;
}
