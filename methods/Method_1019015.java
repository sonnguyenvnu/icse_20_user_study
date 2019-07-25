/** 
 * Throws an exception with the given failure information.
 * @param text Input text that failed to be parsed.
 * @param details Additional failure information.
 * @return Dummy return to satisfy usage in {@link #parse(String)}.
 */
protected T fail(String text,String details){
  String clazz=this.getClass().getSimpleName() + "InsnNode";
  StringBuilder sb=new StringBuilder(clazz + " parse failure: " + text);
  if (details != null)   sb.append('\n').append(details);
  throw new AssemblyParseException(sb.toString());
}
