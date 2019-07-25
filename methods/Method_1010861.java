@Override public void navigate(boolean requestFocus){
  new EditorNavigator(ProjectHelper.fromIdeaProject(getProject())).shallFocus(requestFocus).selectIfChild().open(getSNodeReference());
}
