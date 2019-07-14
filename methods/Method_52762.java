/** 
 * Convert arbitrary String to normal Oracle format, under assumption that the passed image is an Oracle name. <p> This a helper method for PLSQL classes dependent on SimpleNode, that would otherwise have to import PLSQParser. </p>
 * @param image
 * @return
 */
public static String getCanonicalImage(String image){
  return PLSQLParser.canonicalName(image);
}
