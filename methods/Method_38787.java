/** 
 * Lookups pseudo class for given pseudo class name.
 */
public static PseudoClass lookupPseudoClass(final String pseudoClassName){
  PseudoClass pseudoClass=PSEUDO_CLASS_MAP.get(pseudoClassName);
  if (pseudoClass == null) {
    throw new CSSellyException("Unsupported pseudo class: " + pseudoClassName);
  }
  return pseudoClass;
}
