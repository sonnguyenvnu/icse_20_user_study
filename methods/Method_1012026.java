@Override public void navigate(Project mpsProject,boolean useProjectTree,boolean focus){
  new ProjectPaneNavigator(mpsProject).shallFocus(focus).select(myModuleReference);
}
