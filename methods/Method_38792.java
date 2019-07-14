/** 
 * Lookups pseudo function for given pseudo function name.
 */
public static PseudoFunction<?> lookupPseudoFunction(final String pseudoFunctionName){
  PseudoFunction pseudoFunction=PSEUDO_FUNCTION_MAP.get(pseudoFunctionName);
  if (pseudoFunction == null) {
    throw new CSSellyException("Unsupported pseudo function: " + pseudoFunctionName);
  }
  return pseudoFunction;
}
