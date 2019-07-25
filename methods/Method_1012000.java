@Override public void navigate(boolean requestFocus){
  new EditorNavigator(myProject).shallFocus(requestFocus).selectIfChild().open(myTarget.getNodeReference());
}
