/** 
 * Returns a string with legal copyright and licensing information about Assimp. The string may include multiple lines.
 * @return A string containing the legal information.
 */
@NativeType("char const *") public static String aiGetLegalString(){
  long __result=naiGetLegalString();
  return memASCII(__result);
}
