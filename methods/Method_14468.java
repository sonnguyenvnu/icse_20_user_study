@Override public void revert(Project project){
synchronized (project) {
    Column column=project.columnModel.columns.remove(_newColumnIndex);
    project.columnModel.columns.add(_oldColumnIndex,column);
    project.columnModel.columnGroups.clear();
    project.columnModel.columnGroups.addAll(_oldColumnGroups);
    project.update();
  }
}
