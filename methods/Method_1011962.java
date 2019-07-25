@Override public void navigate(boolean focus){
  new EditorNavigator(myProject).shallFocus(focus).selectIfChild().open(myNodePointer);
}
