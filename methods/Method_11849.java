@Override public Runner runnerForClass(Class<?> testClass) throws Exception {
  for (Class<?> currentTestClass=testClass; currentTestClass != null; currentTestClass=getEnclosingClassForNonStaticMemberClass(currentTestClass)) {
    RunWith annotation=currentTestClass.getAnnotation(RunWith.class);
    if (annotation != null) {
      return buildRunner(annotation.value(),testClass);
    }
  }
  return null;
}
