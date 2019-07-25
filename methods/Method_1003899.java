/** 
 * Creates a Rebinder that rebinds keys to the given annotation instance.
 * @param binder A binder to rebind keys in.
 * @param annotation The annotation instance to rebind keys to.
 * @return A Rebinder targeting the given {@code annotationType}.
 */
public static Rebinder rebinder(Binder binder,Annotation annotation){
  return new Rebinder(binder,annotatedKeyFactory(annotation));
}
