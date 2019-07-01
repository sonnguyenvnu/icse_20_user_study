@Override public void _XXXXX_(long lssn,long entryId){
  satisfyPromises(lssn,entryId);
  buffer.release();
synchronized (this) {
    ReferenceCountUtil.release(finalizedBuffer);
  }
}