@Override public long size(){
  return root.size() + prefixLen + suffixLen;
}
