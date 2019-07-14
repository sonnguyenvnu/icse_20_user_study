/** 
 * Returns method parameters once when method is parsed. If method has no parameters, an empty array is returned.
 */
MethodParameter[] getResolvedParameters(){
  if (paramExtractor == null) {
    return MethodParameter.EMPTY_ARRAY;
  }
  if (!paramExtractor.debugInfoPresent) {
    throw new ParamoException("Parameter names not available for method: " + declaringClass.getName() + '#' + methodName);
  }
  return paramExtractor.getMethodParameters();
}
