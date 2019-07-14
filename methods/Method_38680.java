/** 
 * Adds a  {@link jodd.util.Wildcard wildcard} pattern for white-listing classes.
 * @see #setClassMetadataName(String)
 */
public JsonParser allowClass(final String classPattern){
  if (super.classnameWhitelist == null) {
    super.classnameWhitelist=new ArrayList<>();
  }
  classnameWhitelist.add(classPattern);
  return this;
}
