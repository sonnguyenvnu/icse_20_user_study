/** 
 * Get all properties that start with the given prefix.  
 * @param prefix The prefix for which to search.  If it does not end in a "." then one will be added to it for search purposes.
 * @param stripPrefix Whether to strip off the given <code>prefix</code>in the result's keys.
 * @param excludedPrefixes Optional array of fully qualified prefixes toexclude.  For example if <code>prefix</code> is "a.b.c", then  <code>excludedPrefixes</code> might be "a.b.c.ignore".
 * @return Group of <code>Properties</code> that start with the given prefix, optionally have that prefix removed, and do not include properties  that start with one of the given excluded prefixes.
 */
public Properties getPropertyGroup(String prefix,boolean stripPrefix,String[] excludedPrefixes){
  Enumeration<?> keys=props.propertyNames();
  Properties group=new Properties();
  if (!prefix.endsWith(".")) {
    prefix+=".";
  }
  while (keys.hasMoreElements()) {
    String key=(String)keys.nextElement();
    if (key.startsWith(prefix)) {
      boolean exclude=false;
      if (excludedPrefixes != null) {
        for (int i=0; (i < excludedPrefixes.length) && (exclude == false); i++) {
          exclude=key.startsWith(excludedPrefixes[i]);
        }
      }
      if (exclude == false) {
        String value=getStringProperty(key,"");
        if (stripPrefix) {
          group.put(key.substring(prefix.length()),value);
        }
 else {
          group.put(key,value);
        }
      }
    }
  }
  return group;
}
