@Override public Object _XXXXX_(Object vals,int pos,PartitionedEvent[] newValue){
  return delegate.valueArrayUpdateVal(vals,pos,serialize(newValue));
}