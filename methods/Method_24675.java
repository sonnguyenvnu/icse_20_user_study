public EditorHeader createHeader(){
  return new EditorHeader(this){
    public void rebuild(){
      super.rebuild();
      boolean newHasJavaTabs=checkForJavaTabs();
      boolean hasJavaTabsChanged=hasJavaTabs != newHasJavaTabs;
      hasJavaTabs=newHasJavaTabs;
      if (preprocessingService != null) {
        if (hasJavaTabsChanged) {
          preprocessingService.handleHasJavaTabsChange(hasJavaTabs);
          pdex.hasJavaTabsChanged(hasJavaTabs);
          if (hasJavaTabs) {
            setProblemList(Collections.emptyList());
          }
        }
        int currentTabCount=sketch.getCodeCount();
        if (currentTabCount != previousTabCount) {
          previousTabCount=currentTabCount;
          pdex.sketchChanged();
        }
      }
    }
  }
;
}
