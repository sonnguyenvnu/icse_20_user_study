/** 
 * Add a mapping that determines how old class names can be updated to newer class names. Such updates are desirable as the Java code changes from version to version. If the "from" argument ends with *, then it's considered a prefix; otherwise, it's an exact string match.
 * @param from
 * @param to
 */
static public void registerClassMapping(String from,String to){
  classMappings.add(new ClassMapping(from,to.endsWith("*") ? to.substring(0,to.length() - 1) : to));
}
