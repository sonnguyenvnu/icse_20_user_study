protected static final int ltoi(long l){
  return ((int)(l ^ (l >>> 32)));
}
