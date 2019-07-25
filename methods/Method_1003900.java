/** 
 * Creates a Rebinder that rebinds keys to the given annotation type.
 * @param binder A binder to rebind keys in.
 * @param annotationType The annotation type to rebind keys to.
 * @return A Rebinder targeting the given {@code annotationType}.
 */
public static Rebinder rebinder(Binder binder,Class<? extends Annotation> annotationType){
  return new Rebinder(binder,annotatedKeyFactory(annotationType));
}
