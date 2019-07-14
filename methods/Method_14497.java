synchronized public void addColumnGroup(int startColumnIndex,int span,int keyColumnIndex){
  for (  ColumnGroup g : columnGroups) {
    if (g.startColumnIndex == startColumnIndex && g.columnSpan == span) {
      if (g.keyColumnIndex == keyColumnIndex) {
        return;
      }
 else {
        columnGroups.remove(g);
        break;
      }
    }
  }
  ColumnGroup cg=new ColumnGroup(startColumnIndex,span,keyColumnIndex);
  columnGroups.add(cg);
}
