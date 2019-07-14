/** 
 * Creates new instance of given type. In the first try, it tries to use constructor with a  {@link PetiteContainer}. If that files, uses default constructor to builds an instance.
 */
private <T>T newInternalInstance(final Class<T> type,final PetiteContainer petiteContainer) throws Exception {
  T t=null;
  try {
    Constructor<T> ctor=type.getConstructor(PetiteContainer.class);
    t=ctor.newInstance(petiteContainer);
  }
 catch (  NoSuchMethodException nsmex) {
  }
  if (t == null) {
    return ClassUtil.newInstance(type);
  }
  return t;
}
