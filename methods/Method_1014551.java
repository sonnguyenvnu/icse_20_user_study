@Override public Status execute(Target dst,int dstIndex,Target src,int srcIndex,Status status){
  dst.set(dstIndex,src.get(srcIndex));
  return status;
}
