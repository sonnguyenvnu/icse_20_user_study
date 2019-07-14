public static RelationIdentifier get(long[] ids){
  if (ids.length != 3 && ids.length != 4)   throw new IllegalArgumentException("Not a valid relation identifier: " + Arrays.toString(ids));
  for (int i=0; i < 3; i++) {
    if (ids[i] < 0)     throw new IllegalArgumentException("Not a valid relation identifier: " + Arrays.toString(ids));
  }
  return new RelationIdentifier(ids[1],ids[2],ids[0],ids.length == 4 ? ids[3] : 0);
}
