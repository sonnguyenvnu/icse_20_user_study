@Override public synchronized void _XXXXX_(Throwable reason){
  cancelPromises(reason);
  buffer.release();
  ReferenceCountUtil.release(recordSetBuffer);
}