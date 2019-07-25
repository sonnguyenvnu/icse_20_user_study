void navigate(final SNodeReference nodeRef){
  new EditorNavigator(myProject).shallFocus(true).shallSelect(true).open(nodeRef);
}
