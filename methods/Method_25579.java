private static boolean isAnnotationOnType(Symbol sym,TypeAnnotationPosition position){
  if (!position.location.isEmpty()) {
    return false;
  }
switch (sym.getKind()) {
case LOCAL_VARIABLE:
    return position.type == TargetType.LOCAL_VARIABLE;
case FIELD:
  return position.type == TargetType.FIELD;
case CONSTRUCTOR:
case METHOD:
return position.type == TargetType.METHOD_RETURN;
case PARAMETER:
switch (position.type) {
case METHOD_FORMAL_PARAMETER:
return ((MethodSymbol)sym.owner).getParameters().indexOf(sym) == position.parameter_index;
default :
return false;
}
case CLASS:
return false;
default :
throw new AssertionError("unsupported element kind in MoreAnnotation#isAnnotationOnType: " + sym.getKind());
}
}
