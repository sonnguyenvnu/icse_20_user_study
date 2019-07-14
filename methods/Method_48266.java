private void logMutatedEntries(DataOutput out,List<Entry> entries){
  VariableLong.writePositive(out,entries.size());
  for (  Entry add : entries)   BufferUtil.writeEntry(out,add);
}
