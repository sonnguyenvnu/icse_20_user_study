@Override public Status execute(Target dst,int dstIndex,Status status){
  dst.set(dstIndex,~dst.get(dstIndex));
  return status;
}
