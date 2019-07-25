@Override public boolean equivalent(RandomDataInput source,long sourceOffset){
  return source.readInt(sourceOffset) == instance;
}
