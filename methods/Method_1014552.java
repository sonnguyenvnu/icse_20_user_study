@Override public Status execute(Target dst,int dstIndex,int src,Status status){
  dst.set(dstIndex,src);
  return status;
}
