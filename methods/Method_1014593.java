@Override public Status execute(Target dst,int dstIndex,Target src,int srcIndex,Status status){
  int tmp=dst.get(dstIndex);
  dst.set(dstIndex,src.get(srcIndex));
  src.set(srcIndex,tmp);
  return status;
}
