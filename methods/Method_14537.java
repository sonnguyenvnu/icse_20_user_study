private void dumpKeyedGroups(List<KeyedGroup> groups,ColumnModel columnModel){
  for (  KeyedGroup g : groups) {
    String keyColName=columnModel.getColumnByCellIndex(g.keyCellIndex).getName();
    StringBuffer sb=new StringBuffer();
    for (    int ci : g.cellIndices) {
      Column col=columnModel.getColumnByCellIndex(ci);
      if (col != null) {
        sb.append(col.getName()).append(',');
      }
    }
    logger.trace("KeyedGroup " + keyColName + "::" + sb.toString());
  }
}
