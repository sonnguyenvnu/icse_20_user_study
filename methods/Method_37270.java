/** 
 * Defines included property names as public properties of given template class. Sets to black list mode.
 */
public T includeAs(final Class template){
  blacklist=false;
  String[] properties=getAllBeanPropertyNames(template,false);
  include(properties);
  return _this();
}
