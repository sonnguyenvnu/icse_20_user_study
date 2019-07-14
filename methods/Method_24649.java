/** 
 * Translate a java source location to a sketch line id.
 * @param l the location to translate
 * @return the corresponding line id, or null if not found
 */
protected LineID locationToLineID(Location l){
  try {
    return javaToSketchLine(new LineID(l.sourceName(),l.lineNumber() - 1));
  }
 catch (  AbsentInformationException ex) {
    loge("absent information",ex);
    return null;
  }
}
