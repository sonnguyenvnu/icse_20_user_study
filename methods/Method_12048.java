/** 
 * Adds to  {@code errors} if any method in this class is annotated with{@code annotation}, but: <ul> <li>is not public, or <li>takes parameters, or <li>returns something other than void, or <li>is static (given  {@code isStatic is false}), or <li>is not static (given  {@code isStatic is true}). </ul>
 */
protected void validatePublicVoidNoArgMethods(Class<? extends Annotation> annotation,boolean isStatic,List<Throwable> errors){
  List<FrameworkMethod> methods=getTestClass().getAnnotatedMethods(annotation);
  for (  FrameworkMethod eachTestMethod : methods) {
    eachTestMethod.validatePublicVoidNoArg(isStatic,errors);
  }
}
