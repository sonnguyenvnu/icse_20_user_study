/** 
 * Returns true if the leaf node in the  {@link TreePath} from {@code state} sits somewhereunderneath a class or method that is marked as JUnit 3 or 4 test code.
 */
public static boolean isJUnitTestCode(VisitorState state){
  for (  Tree ancestor : state.getPath()) {
    if (ancestor instanceof MethodTree && JUnitMatchers.hasJUnitAnnotation((MethodTree)ancestor,state)) {
      return true;
    }
    if (ancestor instanceof ClassTree && (JUnitMatchers.isTestCaseDescendant.matches((ClassTree)ancestor,state) || hasAnnotation(getSymbol(ancestor),JUNIT4_RUN_WITH_ANNOTATION,state))) {
      return true;
    }
  }
  return false;
}
