private static long[] checkSignature(List<PropertyKey> sig){
  Preconditions.checkArgument(sig.size() == (Sets.newHashSet(sig)).size(),"Signature and sort key cannot contain duplicate types");
  long[] signature=new long[sig.size()];
  for (int i=0; i < sig.size(); i++) {
    PropertyKey key=sig.get(i);
    Preconditions.checkNotNull(key);
    Preconditions.checkArgument(!((PropertyKey)key).dataType().equals(Object.class),"Signature and sort keys must have a proper declared datatype: %s",key.name());
    signature[i]=key.longId();
  }
  return signature;
}
