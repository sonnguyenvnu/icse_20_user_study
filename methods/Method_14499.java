synchronized protected void internalInitialize(){
  generateMaps();
  _rootColumnGroups=new LinkedList<ColumnGroup>(columnGroups);
  Collections.sort(_rootColumnGroups,new Comparator<ColumnGroup>(){
    @Override public int compare(    ColumnGroup o1,    ColumnGroup o2){
      int firstDiff=o1.startColumnIndex - o2.startColumnIndex;
      return firstDiff != 0 ? firstDiff : (o2.columnSpan - o1.columnSpan);
    }
  }
);
  for (int i=_rootColumnGroups.size() - 1; i >= 0; i--) {
    ColumnGroup g=_rootColumnGroups.get(i);
    for (int j=i + 1; j < _rootColumnGroups.size(); j++) {
      ColumnGroup g2=_rootColumnGroups.get(j);
      if (g2.parentGroup == null && g.contains(g2)) {
        g2.parentGroup=g;
        g.subgroups.add(g2);
      }
    }
  }
  for (int i=_rootColumnGroups.size() - 1; i >= 0; i--) {
    if (_rootColumnGroups.get(i).parentGroup != null) {
      _rootColumnGroups.remove(i);
    }
  }
}
