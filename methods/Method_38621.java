/** 
 * Creates alias from target class and target method name.
 */
protected String alias(final Class targetClass,final String targetMethodName){
  return '<' + targetClass.getName() + '#' + targetMethodName + '>';
}
