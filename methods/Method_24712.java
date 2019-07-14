/** 
 * Get a tab by its file name.
 * @param filename the filename to search for.
 * @return the {@link SketchCode} object for the tab, or null if not found
 */
public SketchCode getTab(String filename){
  Sketch s=getSketch();
  for (  SketchCode c : s.getCode()) {
    if (c.getFileName().equals(filename)) {
      return c;
    }
  }
  return null;
}
