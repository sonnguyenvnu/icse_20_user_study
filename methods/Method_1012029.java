@Override public void navigate(Project mpsProject,boolean useProjectTree,boolean focus){
  if (useProjectTree) {
    new ProjectPaneNavigator(mpsProject).shallFocus(focus).select(myNodePointer);
  }
 else {
    new EditorNavigator(mpsProject).shallFocus(focus).selectIfChild().open(myNodePointer);
  }
}
