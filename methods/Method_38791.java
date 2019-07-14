/** 
 * Registers pseudo function.
 */
public static void registerPseudoFunction(final Class<? extends PseudoFunction> pseudoFunctionType){
  PseudoFunction pseudoFunction;
  try {
    pseudoFunction=ClassUtil.newInstance(pseudoFunctionType);
  }
 catch (  Exception ex) {
    throw new CSSellyException(ex);
  }
  PSEUDO_FUNCTION_MAP.put(pseudoFunction.getPseudoFunctionName(),pseudoFunction);
}
