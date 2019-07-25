@Override public Collection<?> cast(Object src,Class<?> toType,String... args) throws FailToCastObjectException {
  Collection coll=createCollection(src,toType);
  for (int i=0; i < Array.getLength(src); i++)   coll.add(Array.get(src,i));
  return coll;
}
