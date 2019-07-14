/** 
 * Creates an  {@link Ordering} from the given factory class. The class must have a public no-argconstructor.
 * @param factoryClass class to use to create the ordering
 * @param annotatedTestClass test class that is annotated with {@link OrderWith}.
 * @throws InvalidOrderingException if the instance could not be created
 */
public static Ordering definedBy(Class<? extends Ordering.Factory> factoryClass,Description annotatedTestClass) throws InvalidOrderingException {
  if (factoryClass == null) {
    throw new NullPointerException("factoryClass cannot be null");
  }
  if (annotatedTestClass == null) {
    throw new NullPointerException("annotatedTestClass cannot be null");
  }
  Ordering.Factory factory;
  try {
    Constructor<? extends Ordering.Factory> constructor=factoryClass.getConstructor();
    factory=constructor.newInstance();
  }
 catch (  NoSuchMethodException e) {
    throw new InvalidOrderingException(String.format(CONSTRUCTOR_ERROR_FORMAT,getClassName(factoryClass),factoryClass.getSimpleName()));
  }
catch (  Exception e) {
    throw new InvalidOrderingException("Could not create ordering for " + annotatedTestClass,e);
  }
  return definedBy(factory,annotatedTestClass);
}
