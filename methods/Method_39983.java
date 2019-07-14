/** 
 * Resolve validation context for provided target class.
 * @see #addClassChecks(Class)
 */
public static ValidationContext resolveFor(final Class<?> target){
  ValidationContext vc=new ValidationContext();
  vc.addClassChecks(target);
  return vc;
}
