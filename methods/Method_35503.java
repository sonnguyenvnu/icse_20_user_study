/** 
 * @param cp code point to test
 * @return true if the code point is not valid for an XML
 */
private static boolean mustEscape(int cp){
  return (Character.isISOControl(cp) && cp != 0x9 && cp != 0xA && cp != 0xD) || !((cp >= 0x20 && cp <= 0xD7FF) || (cp >= 0xE000 && cp <= 0xFFFD) || (cp >= 0x10000 && cp <= 0x10FFFF));
}
