/** 
 * Switch to a tab.
 * @param tabFileName the file name identifying the tab. (as in{@link SketchCode#getFileName()})
 */
public void switchToTab(String tabFileName){
  Sketch s=getSketch();
  for (int i=0; i < s.getCodeCount(); i++) {
    if (tabFileName.equals(s.getCode(i).getFileName())) {
      s.setCurrentCode(i);
      break;
    }
  }
}
