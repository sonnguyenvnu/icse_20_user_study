/** 
 * @param variableType The variable type.
 * @return boolean true if variableType is a primitive or wrapper
 */
private boolean filterPrimitivesAndWrappers(String variableType){
  return "int".equals(variableType) || "Integer".equals(variableType) || "char".equals(variableType) || "Character".equals(variableType) || "double".equals(variableType) || "long".equals(variableType) || "short".equals(variableType) || "float".equals(variableType) || "byte".equals(variableType) || "boolean".equals(variableType);
}
