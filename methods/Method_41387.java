/** 
 * convert the JobDataMap into a list of properties
 */
protected Map<?,?> convertFromProperty(Properties properties) throws IOException {
  return new HashMap<Object,Object>(properties);
}
