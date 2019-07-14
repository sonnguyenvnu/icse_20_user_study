/** 
 * Registers pseudo class.
 */
public static void registerPseudoClass(final Class<? extends PseudoClass> pseudoClassType){
  PseudoClass pseudoClass;
  try {
    pseudoClass=ClassUtil.newInstance(pseudoClassType);
  }
 catch (  Exception ex) {
    throw new CSSellyException(ex);
  }
  PSEUDO_CLASS_MAP.put(pseudoClass.getPseudoClassName(),pseudoClass);
}
