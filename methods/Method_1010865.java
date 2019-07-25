@Override public void navigate(final boolean requestFocus){
  new EditorNavigator(ProjectHelper.fromIdeaProject(getProject())).shallFocus(requestFocus).open(new SNodePointer(myModel.getSModelReference(),myNodeId));
}
