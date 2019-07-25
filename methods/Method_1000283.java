@Override public Map cast(Object src,Class<?> toType,String... args) throws FailToCastObjectException {
  if (null == args || args.length == 0)   throw Lang.makeThrow(FailToCastObjectException.class,"For the elements in array %s[], castors don't know which one is the key field.",src.getClass().getComponentType().getName());
  return Lang.array2map((Class<Map<Object,Object>>)toType,src,args[0]);
}
