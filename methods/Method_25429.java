@Nullable private static MethodSymbol getConstructor(ExpressionTree tree){
switch (tree.getKind()) {
case NEW_CLASS:
case METHOD_INVOCATION:
    break;
default :
  return null;
}
Symbol sym=ASTHelpers.getSymbol(tree);
if (!(sym instanceof MethodSymbol)) {
return null;
}
MethodSymbol method=(MethodSymbol)sym;
if (!method.isConstructor()) {
return null;
}
return method;
}
