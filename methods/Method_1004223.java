@Override public boolean equivalent(RandomDataInput source,long sourceOffset){
  return source.readLong(sourceOffset) == instance;
}
