/** 
 * Gets the data source as a pseudo file name (faux-file). Adding a suffix matching the source object type ensures that the appropriate language parser is used.
 */
public String getPseudoFileName(){
  return String.format("/Database/%s/%s/%s%s",getSchema(),getType(),getName(),getSuffixFromType());
}
