public void reset(boolean openCurrentProject,String pathToProject){
  myOpenCurrentProject.setSelected(openCurrentProject);
  myProjectPath.setEditable(!(openCurrentProject));
  myProjectPath.setText(pathToProject);
}
