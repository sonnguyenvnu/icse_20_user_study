/** 
 * Converts java-class name ("foo.Bar") to bytecode-name ("foo/bar").
 */
public static String typeToSignature(final Class type){
  return typeToSignature(type.getName());
}
