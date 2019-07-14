/** 
 * Returns the packages in order.
 * @deprecated Use {@link JavaTypeQualifiedName#getPackageList()} ()}. Will be removed in 7.0.0
 */
@Deprecated public String[] getPackages(){
  return getClassName().getPackageList().toArray(new String[0]);
}
