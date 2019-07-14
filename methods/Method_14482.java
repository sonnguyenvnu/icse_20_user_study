@Override public void apply(Project project){
synchronized (project) {
    List<Row> oldRows=project.rows;
    List<Row> newRows=new ArrayList<Row>(oldRows.size());
    for (    Integer oldIndex : _rowIndices) {
      newRows.add(oldRows.get(oldIndex));
    }
    project.rows.clear();
    project.rows.addAll(newRows);
    project.update();
  }
}
