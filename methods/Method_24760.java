/** 
 * Find the parent of the expression in a().b, this would give me the return type of a(), so that we can find all children of a() begininng with b
 * @param nearestNode
 * @param expression
 * @return
 */
public static ASTNode resolveExpression(ASTNode nearestNode,ASTNode expression,boolean noCompare){
  log("Resolving " + getNodeAsString(expression) + " noComp " + noCompare);
  if (expression instanceof SimpleName) {
    return findDeclaration2(((SimpleName)expression),nearestNode);
  }
 else   if (expression instanceof MethodInvocation) {
    log("3. Method Invo " + ((MethodInvocation)expression).getName());
    return findDeclaration2(((MethodInvocation)expression).getName(),nearestNode);
  }
 else   if (expression instanceof FieldAccess) {
    log("2. Field access " + getNodeAsString(((FieldAccess)expression).getExpression()) + "|||" + getNodeAsString(((FieldAccess)expression).getName()));
    if (noCompare) {
      return findDeclaration2(((FieldAccess)expression).getName(),nearestNode);
    }
 else {
      return resolveExpression(nearestNode,((FieldAccess)expression).getExpression(),true);
    }
  }
 else   if (expression instanceof QualifiedName) {
    log("1. Resolving " + ((QualifiedName)expression).getQualifier() + " ||| " + ((QualifiedName)expression).getName());
    if (noCompare) {
      return findDeclaration2(((QualifiedName)expression).getName(),nearestNode);
    }
 else {
      return findDeclaration2(((QualifiedName)expression).getQualifier(),nearestNode);
    }
  }
  return null;
}
