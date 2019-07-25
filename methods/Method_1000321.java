@Override public Class<?> cast(String src,Class toType,String... args){
  if (null == src)   return null;
  Class<?> c=map.get(src);
  if (null != c)   return c;
  try {
    return Lang.loadClass(src);
  }
 catch (  ClassNotFoundException e) {
    throw new FailToCastObjectException(format("String '%s' can not cast to Class<?>!",src));
  }
}
