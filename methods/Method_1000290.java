@Override public Object cast(Collection src,Class<?> toType,String... args) throws FailToCastObjectException {
  Class<?> compType=toType.getComponentType();
  Object ary=Array.newInstance(compType,src.size());
  int index=0;
  for (Iterator it=src.iterator(); it.hasNext(); ) {
    Array.set(ary,index++,Castors.me().castTo(it.next(),compType));
  }
  return ary;
}
