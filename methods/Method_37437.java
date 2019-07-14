/** 
 * Compares two byte arrays in length-constant time. This comparison method is used so that password hashes cannot be extracted from an on-line system using a timing attack and then attacked off-line.
 * @param a the first byte array
 * @param b the second byte array
 * @return true if both byte arrays are the same, false if not
 */
private static boolean slowEquals(final byte[] a,final byte[] b){
  int diff=a.length ^ b.length;
  for (int i=0; i < a.length && i < b.length; i++) {
    diff|=a[i] ^ b[i];
  }
  return diff == 0;
}
