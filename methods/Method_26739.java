/** 
 * Similar to  {@link Class#asSubclass(Class)}, but it accepts a  {@link TypeToken} so it handlesgenerics better.
 */
@SuppressWarnings("unchecked") private static <T>Class<? extends T> asSubclass(Class<?> klass,TypeToken<T> token){
  if (!token.isSupertypeOf(klass)) {
    throw new ClassCastException(klass + " is not assignable to " + token);
  }
  return (Class<? extends T>)klass;
}
