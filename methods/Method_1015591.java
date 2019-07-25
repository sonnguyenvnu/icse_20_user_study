/** 
 * Sanitizes bucket and folder names according to AWS guidelines 
 */
protected static String sanitize(final String name){
  String retval=name;
  retval=retval.replace('/','-');
  retval=retval.replace('\\','-');
  return retval;
}
