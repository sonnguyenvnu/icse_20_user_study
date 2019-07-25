/** 
 * Returns the constructor or method containing the expression.
 */
public CtBehavior where(){
  MethodInfo mi=thisMethod;
  CtBehavior[] cb=thisClass.getDeclaredBehaviors();
  for (int i=cb.length - 1; i >= 0; --i)   if (cb[i].getMethodInfo2() == mi)   return cb[i];
  CtConstructor init=thisClass.getClassInitializer();
  if (init != null && init.getMethodInfo2() == mi)   return init;
  for (int i=cb.length - 1; i >= 0; --i) {
    if (thisMethod.getName().equals(cb[i].getMethodInfo2().getName()) && thisMethod.getDescriptor().equals(cb[i].getMethodInfo2().getDescriptor())) {
      return cb[i];
    }
  }
  throw new RuntimeException("fatal: not found");
}
