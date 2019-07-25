/** 
 * Converts a  {@link ConcreteMethod} to a {@link RopMethod}.
 * @param method {@code non-null;} method to convert
 * @param advice {@code non-null;} translation advice to use
 * @param methods {@code non-null;} list of methods defined by the classthat defines  {@code method}.
 * @return {@code non-null;} the converted instance
 */
public static RopMethod convert(ConcreteMethod method,TranslationAdvice advice,MethodList methods,DexOptions dexOptions){
  try {
    Ropper r=new Ropper(method,advice,methods,dexOptions);
    r.doit();
    return r.getRopMethod();
  }
 catch (  SimException ex) {
    ex.addContext("...while working on method " + method.getNat().toHuman());
    throw ex;
  }
}
