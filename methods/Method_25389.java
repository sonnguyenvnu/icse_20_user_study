/** 
 * Matches the boolean constant ( {@link Boolean#TRUE} or {@link Boolean#FALSE}) corresponding to the given value.
 */
public static Matcher<ExpressionTree> booleanConstant(boolean value){
  return (expressionTree,state) -> {
    if (expressionTree instanceof JCFieldAccess) {
      Symbol symbol=getSymbol(expressionTree);
      if (symbol.isStatic() && state.getTypes().unboxedTypeOrType(symbol.type).getTag() == TypeTag.BOOLEAN) {
        return ((value && symbol.getSimpleName().contentEquals("TRUE")) || symbol.getSimpleName().contentEquals("FALSE"));
      }
    }
    return false;
  }
;
}
