/** 
 * Converts java-class name ("foo.Bar") to bytecode-signature ("foo/bar").
 */
public static String typeToSignature(final String className){
  return className.replace('.','/');
}
