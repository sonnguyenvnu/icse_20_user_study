/** 
 * Creates alias from target object and target method name. If classname contains a '$' sign, everything will be stripped after it (to get the real name, if action class is proxified).
 */
protected String alias(final Object target,final String targetMethodName){
  String targetClassName=target.getClass().getName();
  targetClassName=StringUtil.cutToIndexOf(targetClassName,'$');
  return '<' + targetClassName + '#' + targetMethodName + '>';
}
