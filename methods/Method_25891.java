/** 
 * Returns whether an input  {@link Symbol} is a format string in a {@link FormatMethod}. This is true if the  {@link Symbol} is a {@link String} parameter in a {@link FormatMethod} and iseither: <ol> <li>Annotated with  {@link FormatString}<li>The first  {@link String} parameter in the method with no other parameters annotated{@link FormatString}. </ol>
 */
private static boolean isFormatStringParameter(Symbol formatString,VisitorState state){
  Type stringType=state.getSymtab().stringType;
  if (!ASTHelpers.isSameType(formatString.type,stringType,state) || !(formatString.owner instanceof MethodSymbol) || !ASTHelpers.hasAnnotation(formatString.owner,FormatMethod.class,state)) {
    return false;
  }
  if (ASTHelpers.hasAnnotation(formatString,FormatString.class,state)) {
    return true;
  }
  MethodSymbol owner=(MethodSymbol)formatString.owner;
  boolean formatStringFound=false;
  for (  Symbol param : owner.getParameters()) {
    if (param == formatString) {
      formatStringFound=true;
    }
    if (ASTHelpers.isSameType(param.type,stringType,state)) {
      if (!formatStringFound) {
        return false;
      }
 else       if (ASTHelpers.hasAnnotation(param,FormatString.class,state)) {
        return false;
      }
    }
  }
  return true;
}
