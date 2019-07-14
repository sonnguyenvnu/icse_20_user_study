/** 
 * Translate a line (index) from sketch space to java space.
 * @param sketchLine the sketch line id
 * @return the corresponding java line id or null if failed to translate
 */
public LineID sketchToJavaLine(LineID sketchLine){
  sketchLine=runtimeToOriginalLine(sketchLine);
  SketchCode tab=editor.getTab(sketchLine.fileName());
  if (tab == null) {
    return null;
  }
  if (tab.isExtension("java")) {
    return sketchLine;
  }
  LineID javaLine=new LineID(editor.getSketch().getName() + ".java",sketchLine.lineIdx() + tab.getPreprocOffset());
  return javaLine;
}
