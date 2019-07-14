@Override public void revert(Project project){
synchronized (project) {
    project.rows.clear();
    project.rows.addAll(_oldRows);
    for (int i=0; i < _columnNames.size(); i++) {
      project.columnModel.columns.remove(_columnInsertIndex);
    }
    project.update();
  }
}
