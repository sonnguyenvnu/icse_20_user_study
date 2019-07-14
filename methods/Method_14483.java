@Override public void revert(Project project){
synchronized (project) {
    int count=project.rows.size();
    List<Row> newRows=project.rows;
    List<Row> oldRows=new ArrayList<Row>(count);
    for (int r=0; r < count; r++) {
      oldRows.add(null);
    }
    for (int newIndex=0; newIndex < count; newIndex++) {
      int oldIndex=_rowIndices.get(newIndex);
      Row row=newRows.get(newIndex);
      oldRows.set(oldIndex,row);
    }
    project.rows.clear();
    project.rows.addAll(oldRows);
    project.update();
  }
}
