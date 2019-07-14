/** 
 * Create new constraint. The following rules are used: <ul> <li>use default constructor if exist.</li> <li>otherwise, use constructor with ValidationContext parameter.</li> </ul>
 */
protected <V extends ValidationConstraint>V newConstraint(final Class<V> constraint,final Class targetType) throws Exception {
  Constructor<V> ctor;
  try {
    ctor=constraint.getConstructor();
    return ctor.newInstance();
  }
 catch (  NoSuchMethodException ignore) {
    ctor=constraint.getConstructor(ValidationContext.class);
    return ctor.newInstance(resolveFor(targetType));
  }
}
