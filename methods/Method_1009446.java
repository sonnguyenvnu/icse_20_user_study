@Override public StringMapEntryChange reversed(){
  return new StringMapEntryAdd(key,valueBefore);
}
