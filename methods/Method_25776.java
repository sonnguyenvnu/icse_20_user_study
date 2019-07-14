/** 
 * Returns true if the given method symbol has a  {@code @Test(expected=...)} annotation. 
 */
private static boolean isExpectedExceptionTest(MethodSymbol sym,VisitorState state){
  Compound attribute=sym.attribute(state.getSymbolFromString(JUnitMatchers.JUNIT4_TEST_ANNOTATION));
  if (attribute == null) {
    return false;
  }
  return attribute.member(state.getName("expected")) != null;
}
