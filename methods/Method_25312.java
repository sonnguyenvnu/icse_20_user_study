/** 
 * Returns a human-friendly name of the given  {@code typeName} for use in fixes.<p>This should be used if the type may not be loaded.
 */
public static String qualifyType(VisitorState state,SuggestedFix.Builder fix,String typeName){
  for (int startOfClass=typeName.indexOf('.'); startOfClass > 0; startOfClass=typeName.indexOf('.',startOfClass + 1)) {
    int endOfClass=typeName.indexOf('.',startOfClass + 1);
    if (endOfClass < 0) {
      endOfClass=typeName.length();
    }
    if (!Character.isUpperCase(typeName.charAt(startOfClass + 1))) {
      continue;
    }
    String className=typeName.substring(startOfClass + 1);
    Symbol found=FindIdentifiers.findIdent(className,state,KindSelector.VAL_TYP);
    if (found == null) {
      fix.addImport(typeName.substring(0,endOfClass));
      return className;
    }
    if (found.getQualifiedName().contentEquals(typeName)) {
      return className;
    }
  }
  return typeName;
}
