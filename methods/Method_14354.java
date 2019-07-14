static public void createColumnsFromImport(Project project,ImportColumnGroup columnGroup){
  int startColumnIndex=project.columnModel.columns.size();
  List<ImportColumn> columns=new ArrayList<ImportColumn>(columnGroup.columns.values());
  Collections.sort(columns,new Comparator<ImportColumn>(){
    @Override public int compare(    ImportColumn o1,    ImportColumn o2){
      if (o1.blankOnFirstRow != o2.blankOnFirstRow) {
        return o1.blankOnFirstRow ? 1 : -1;
      }
      int c=o2.nonBlankCount - o1.nonBlankCount;
      return c != 0 ? c : (o1.name.length() - o2.name.length());
    }
  }
);
  for (int i=0; i < columns.size(); i++) {
    ImportColumn c=columns.get(i);
    Column column=new com.google.refine.model.Column(c.cellIndex,c.name);
    project.columnModel.columns.add(column);
  }
  List<ImportColumnGroup> subgroups=new ArrayList<ImportColumnGroup>(columnGroup.subgroups.values());
  Collections.sort(subgroups,new Comparator<ImportColumnGroup>(){
    @Override public int compare(    ImportColumnGroup o1,    ImportColumnGroup o2){
      int c=o2.nonBlankCount - o1.nonBlankCount;
      return c != 0 ? c : (o1.name.length() - o2.name.length());
    }
  }
);
  for (  ImportColumnGroup g : subgroups) {
    createColumnsFromImport(project,g);
  }
  int endColumnIndex=project.columnModel.columns.size();
  int span=endColumnIndex - startColumnIndex;
  if (span > 1 && span < project.columnModel.columns.size()) {
    project.columnModel.addColumnGroup(startColumnIndex,span,startColumnIndex);
  }
}
