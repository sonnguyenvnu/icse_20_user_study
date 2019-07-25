private boolean close(){
  projectClosed();
  assert getProjectModules().isEmpty();
  return true;
}
