protected List<KeyedGroup> computeKeyedGroups(ColumnModel columnModel){
  List<KeyedGroup> keyedGroups=new ArrayList<KeyedGroup>();
  addRootKeyedGroup(columnModel,keyedGroups);
  for (  ColumnGroup group : columnModel.columnGroups) {
    if (group.keyColumnIndex >= 0) {
      KeyedGroup keyedGroup=new KeyedGroup();
      keyedGroup.keyCellIndex=columnModel.columns.get(group.keyColumnIndex).getCellIndex();
      keyedGroup.cellIndices=new int[group.columnSpan - 1];
      int c=0;
      for (int i=0; i < group.columnSpan; i++) {
        int columnIndex=group.startColumnIndex + i;
        if (columnIndex != group.keyColumnIndex && columnIndex < columnModel.columns.size()) {
          int cellIndex=columnModel.columns.get(columnIndex).getCellIndex();
          keyedGroup.cellIndices[c++]=cellIndex;
        }
      }
      keyedGroups.add(keyedGroup);
    }
  }
  Collections.sort(keyedGroups,new Comparator<KeyedGroup>(){
    @Override public int compare(    KeyedGroup o1,    KeyedGroup o2){
      return o2.cellIndices.length - o1.cellIndices.length;
    }
  }
);
  dumpKeyedGroups(keyedGroups,columnModel);
  return keyedGroups;
}
