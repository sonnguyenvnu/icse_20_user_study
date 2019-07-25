@Override public Status execute(Target dst,int dstIndex,int src,Status status){
  int a=(char)dst.get(dstIndex);
  int b=(char)src;
  return add(a,b,status,dst,dstIndex);
}
