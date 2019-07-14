/** 
 * Filters variable type - we don't want primitives, wrappers, strings, etc. This needs more work. I'd like to filter out super types and perhaps interfaces
 * @param variableType The variable type.
 * @return boolean true if variableType is not what we care about
 */
private boolean filterTypes(String variableType){
  return variableType != null && (variableType.startsWith("java.lang.") || "String".equals(variableType) || filterPrimitivesAndWrappers(variableType));
}
