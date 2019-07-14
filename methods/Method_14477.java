@Override public void revert(Project project){
synchronized (project) {
    project.rows.clear();
    project.rows.addAll(_oldRows);
    project.update();
  }
}
