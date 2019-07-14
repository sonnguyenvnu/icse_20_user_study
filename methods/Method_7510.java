public static NativeByteBuffer wrap(long address){
  NativeByteBuffer result=addressWrapper.get();
  if (address != 0) {
    if (!result.reused) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("forgot to reuse?");
      }
    }
    result.address=address;
    result.reused=false;
    result.buffer=native_getJavaByteBuffer(address);
    result.buffer.limit(native_limit(address));
    int position=native_position(address);
    if (position <= result.buffer.limit()) {
      result.buffer.position(position);
    }
    result.buffer.order(ByteOrder.LITTLE_ENDIAN);
  }
  return result;
}
