private String getUserKey(String internalKey){
  String k=internalKey;
  if (null != prefix) {
    Preconditions.checkState(k.startsWith(prefix),"key %s does not start with prefix %s",internalKey,prefix);
    Preconditions.checkState(internalKey.length() > prefix.length());
    k=internalKey.substring(prefix.length());
  }
  return k;
}
