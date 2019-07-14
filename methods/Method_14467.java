static public List<ColumnGroup> readOldColumnGroups(LineNumberReader reader,int oldColumnGroupCount) throws Exception {
  List<ColumnGroup> oldColumnGroups=new ArrayList<ColumnGroup>(oldColumnGroupCount);
  for (int i=0; i < oldColumnGroupCount; i++) {
    String line=reader.readLine();
    if (line != null) {
      oldColumnGroups.add(ColumnGroup.load(line));
    }
  }
  return oldColumnGroups;
}
