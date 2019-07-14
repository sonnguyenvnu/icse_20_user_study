/** 
 * Removes the whitelist of allowed classes.
 * @see #setClassMetadataName(String)
 */
public JsonParser allowAllClasses(){
  classnameWhitelist=null;
  return this;
}
