@Override Nullness fieldNullness(@Nullable ClassAndField accessed,@Nullable AccessPath path,AccessPathValues<Nullness> store){
  if (accessed == null) {
    return defaultAssumption;
  }
  return standardFieldNullness(accessed,path,store);
}
