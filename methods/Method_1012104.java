private void relayout(){
  if (myRoots.isEmpty()) {
    return;
  }
  int y=0;
  int x=0;
  int maxWidth=0;
  for (  LanguageHierarchiesComponent.ConceptContainer root : myRoots) {
    root.updateSubtreeWidth();
    maxWidth=Math.max(maxWidth,root.getSubtreeWidth());
  }
  myHeight=relayoutChildren(myRoots,x,y,true);
  myWidth=maxWidth;
}
