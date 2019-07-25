@Override public void navigate(boolean focus){
  new ProjectPaneNavigator(myProject).shallFocus(focus).select(myModuleReference);
}
