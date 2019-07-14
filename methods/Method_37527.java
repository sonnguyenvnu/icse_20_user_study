/** 
 * Returns type signature bytes used for searching in class file.
 */
public static byte[] bytecodeSignatureOfType(final Class type){
  final String name='L' + type.getName().replace('.','/') + ';';
  return name.getBytes();
}
