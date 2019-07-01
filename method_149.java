public StreamWindow _XXXXX_(long start,long end,long margin){
  return OnHeapStrategy.INSTANCE.createWindow(start,end,margin,this);
}