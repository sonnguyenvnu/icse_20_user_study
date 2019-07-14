/** 
 * hexChar?int
 * @param hexChar hex????
 * @return 0..15
 */
private static int hex2Dec(char hexChar){
  if (hexChar >= '0' && hexChar <= '9') {
    return hexChar - '0';
  }
 else   if (hexChar >= 'A' && hexChar <= 'F') {
    return hexChar - 'A' + 10;
  }
 else {
    throw new IllegalArgumentException();
  }
}
