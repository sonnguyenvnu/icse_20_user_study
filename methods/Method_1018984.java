/** 
 * @param internal Internal class name.
 * @return Standard class name.
 */
private static String normalize(String internal){
  return internal.replace("/",".").replace("$",".");
}
