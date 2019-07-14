private static long makeTemporary(long id){
  Preconditions.checkArgument(id > 0);
  return (1L << 63) | id;
}
