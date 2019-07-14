/** 
 * Returns true if this parameter is an enum identifier 
 */
private static boolean isEnumIdentifier(Parameter parameter){
switch (parameter.kind()) {
case IDENTIFIER:
case MEMBER_SELECT:
    break;
default :
  return false;
}
TypeSymbol typeSymbol=parameter.type().tsym;
if (typeSymbol != null) {
return typeSymbol.getKind() == ElementKind.ENUM;
}
return false;
}
