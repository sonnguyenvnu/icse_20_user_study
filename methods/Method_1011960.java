@Override public void navigate(boolean focus){
  SModel descriptor=myModelReference.resolve(myProject.getRepository());
  if (descriptor == null) {
    return;
  }
  new ProjectPaneNavigator(myProject).shallFocus(focus).select(myModelReference);
}
