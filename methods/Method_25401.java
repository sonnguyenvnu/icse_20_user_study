/** 
 * Matches a constructor declaration in a specific enclosing class.
 * @param className The fully-qualified name of the enclosing class, e.g."com.google.common.base.Preconditions"
 */
public static Matcher<MethodTree> constructorOfClass(String className){
  return (methodTree,state) -> {
    Symbol symbol=getSymbol(methodTree);
    return symbol.getEnclosingElement().getQualifiedName().contentEquals(className) && symbol.isConstructor();
  }
;
}
