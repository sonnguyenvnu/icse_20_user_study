public StringMapChanges create(){
  List<StringMapEntryChange> list=new ArrayList<>(changes.values());
  return new StringMapChanges(list);
}
