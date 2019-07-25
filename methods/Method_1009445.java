@Override public StringMapEntryChange reversed(){
  return new StringMapEntryDelete(key,value);
}
