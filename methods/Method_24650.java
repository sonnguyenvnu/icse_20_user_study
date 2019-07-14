/** 
 * Translate a line (index) from java space to sketch space.
 * @param javaLine the java line id
 * @return the corresponding sketch line id or null if failed to translate
 */
public LineID javaToSketchLine(LineID javaLine){
  Sketch sketch=editor.getSketch();
  SketchCode tab=editor.getTab(javaLine.fileName());
  if (tab != null && tab.isExtension("java")) {
    return originalToRuntimeLine(javaLine);
  }
  if (!javaLine.fileName().equals(sketch.getName() + ".java")) {
    return null;
  }
  for (int i=sketch.getCodeCount() - 1; i >= 0; i--) {
    tab=sketch.getCode(i);
    if (tab.isExtension("pde") && tab.getPreprocOffset() <= javaLine.lineIdx()) {
      final int index=javaLine.lineIdx() - tab.getPreprocOffset();
      return originalToRuntimeLine(new LineID(tab.getFileName(),index));
    }
  }
  return null;
}
