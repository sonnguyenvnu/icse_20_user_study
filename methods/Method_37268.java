/** 
 * Returns an array of bean properties. If bean is a <code>Map</code>, all its keys will be returned.
 */
protected String[] resolveProperties(final Object bean,final boolean declared){
  String[] properties;
  if (bean instanceof Map) {
    Set keys=((Map)bean).keySet();
    properties=new String[keys.size()];
    int ndx=0;
    for (    Object key : keys) {
      properties[ndx]=key.toString();
      ndx++;
    }
  }
 else {
    properties=getAllBeanPropertyNames(bean.getClass(),declared);
  }
  return properties;
}
