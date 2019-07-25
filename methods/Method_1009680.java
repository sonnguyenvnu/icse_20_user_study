private DbEntry adapt(Entry<Slice,Slice> entry){
  return new DbEntry(entry.getKey(),entry.getValue());
}
