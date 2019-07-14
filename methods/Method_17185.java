private boolean casProducerLimit(long expect,long newValue){
  return UNSAFE.compareAndSwapLong(this,P_LIMIT_OFFSET,expect,newValue);
}
