/** 
 * Creates a path binder by parsing the given path binding specification. <p> This method is used by methods such as  {@link Chain#path(String,Class)}. <p> See <a href="../handling/Chain.html#path-binding">the section on path binding as part of the Chain documentation</a> for the format of the string.
 * @param pathBindingSpec the path binding specification.
 * @param exhaustive whether the binder must match the entire unbound path (false for a prefix match)
 * @return a path binder constructed from the given path binding specification
 */
static PathBinder parse(String pathBindingSpec,boolean exhaustive){
  return DefaultPathBinderBuilder.parse(pathBindingSpec,exhaustive);
}
