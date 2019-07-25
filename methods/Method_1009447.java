@Override public StringMapEntryChange reversed(){
  return new StringMapEntryModify(key,value,valueBefore);
}
