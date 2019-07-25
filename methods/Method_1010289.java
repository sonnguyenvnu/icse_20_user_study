@NotNull public static EnumerationLiteralsIndex build(long uuidHigh,long uuidLow,long enumeration,@NotNull long... members){
  IndexContainer indexContainer=new IndexContainer();
  for (int i=0; i < members.length; i++) {
    indexContainer.put(members[i],i);
  }
  indexContainer.compact();
  return new EnumerationLiteralsIndex(MetaIdFactory.dataTypeId(uuidHigh,uuidLow,enumeration),indexContainer);
}
