/** 
 * Load a tab that the user added to the sketch or modified with an external editor.
 */
public void loadNewTab(String filename,String ext,boolean newAddition){
  if (newAddition) {
    insertCode(new SketchCode(new File(folder,filename),ext));
  }
 else {
    replaceCode(new SketchCode(new File(folder,filename),ext));
  }
  sortCode();
}
