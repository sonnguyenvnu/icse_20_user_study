protected void addRootKeyedGroup(ColumnModel columnModel,List<KeyedGroup> keyedGroups){
  int count=columnModel.getMaxCellIndex() + 1;
  if (count > 0 && columnModel.getKeyColumnIndex() < columnModel.columns.size()) {
    KeyedGroup rootKeyedGroup=new KeyedGroup();
    rootKeyedGroup.cellIndices=new int[count - 1];
    rootKeyedGroup.keyCellIndex=columnModel.columns.get(columnModel.getKeyColumnIndex()).getCellIndex();
    for (int i=0; i < count; i++) {
      if (i < rootKeyedGroup.keyCellIndex) {
        rootKeyedGroup.cellIndices[i]=i;
      }
 else       if (i > rootKeyedGroup.keyCellIndex) {
        rootKeyedGroup.cellIndices[i - 1]=i;
      }
    }
    keyedGroups.add(rootKeyedGroup);
  }
}
