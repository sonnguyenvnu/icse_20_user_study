public void viewPortChanged(int firstVisibleIndex,int lastVisibleIndex,int firstFullyVisibleIndex,int lastFullyVisibleIndex,@ViewportInfo.State int state){
  final Section currentSection;
synchronized (this) {
    currentSection=mCurrentSection;
  }
  if (currentSection != null) {
    viewPortChangedRecursive(currentSection,firstVisibleIndex,lastVisibleIndex,firstFullyVisibleIndex,lastFullyVisibleIndex,state);
  }
}
