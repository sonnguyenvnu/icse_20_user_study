private void reset(){
  for (  Long2IntMap.Entry entry : counts.long2IntEntrySet()) {
    entry.setValue(entry.getIntValue() / 2);
  }
  size=(size / 2);
}
