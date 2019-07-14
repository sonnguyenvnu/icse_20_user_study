/** 
 * Returns true if the declared variable's type is inferred by the compiler. In Java 8, this can happen if it's in a formal parameter of a lambda with an inferred type (e.g.  {@code (a, b) -> a + b}). Since Java 10, the type of local variables can be inferred too, e.g.  {@code var i = 2;}. <p>This method returns true for declarator IDs in those contexts, in which case  {@link #getTypeNode()} returns {@code null}, since the type node is absent.
 */
public boolean isTypeInferred(){
  return isLambdaParamWithNoType() || isLocalVariableTypeInferred() || isLambdaTypeInferred();
}
