/** 
 * Returns new suffix or <code>null</code> if suffix is not in use.
 */
protected String resolveClassNameSuffix(){
  String classNameSuffix=proxetta.getClassNameSuffix();
  if (classNameSuffix == null) {
    return null;
  }
  if (!proxetta.isVariableClassName()) {
    return classNameSuffix;
  }
  suffixCounter++;
  return classNameSuffix + suffixCounter;
}
