/** 
 * Matches if all of the following conditions are true: 1) The method matches  {@link #methodMatcher()}, (looks like setUp() or tearDown(), and none of the overrides in the hierarchy of the method have the appropriate @Before or @After annotations) 2) The method is not annotated with @Test 3) The enclosing class has an @RunWith annotation and does not extend TestCase. This marks that the test is intended to run with JUnit 4.
 */
@Override public Description matchMethod(MethodTree methodTree,VisitorState state){
  boolean matches=allOf(methodMatcher(),not(hasAnnotationOnAnyOverriddenMethod(JUNIT_TEST)),enclosingClass(isJUnit4TestClass)).matches(methodTree,state);
  if (!matches) {
    return Description.NO_MATCH;
  }
  Description description;
  for (  AnnotationReplacements replacement : annotationReplacements()) {
    description=tryToReplaceAnnotation(methodTree,state,replacement.badAnnotation,replacement.goodAnnotation);
    if (description != null) {
      return description;
    }
  }
  String correctAnnotation=correctAnnotation();
  String unqualifiedClassName=getUnqualifiedClassName(correctAnnotation);
  for (  AnnotationTree annotationNode : methodTree.getModifiers().getAnnotations()) {
    Symbol annoSymbol=ASTHelpers.getSymbol(annotationNode);
    if (annoSymbol.getSimpleName().contentEquals(unqualifiedClassName)) {
      SuggestedFix.Builder suggestedFix=SuggestedFix.builder().removeImport(annoSymbol.getQualifiedName().toString()).addImport(correctAnnotation);
      makeProtectedPublic(methodTree,state,suggestedFix);
      return describeMatch(annotationNode,suggestedFix.build());
    }
  }
  SuggestedFix.Builder suggestedFix=SuggestedFix.builder().addImport(correctAnnotation);
  makeProtectedPublic(methodTree,state,suggestedFix);
  suggestedFix.prefixWith(methodTree,"@" + unqualifiedClassName + "\n");
  return describeMatch(methodTree,suggestedFix.build());
}
