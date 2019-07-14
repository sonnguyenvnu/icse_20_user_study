/** 
 * Returns the corresponding value if the key is found. Otherwise returns -1.
 * @param key search key
 * @return found value
 */
public int exactMatchSearch(byte[] key){
  int unit=_array[0];
  int nodePos=0;
  for (  byte b : key) {
    nodePos^=((unit >>> 10) << ((unit & (1 << 9)) >>> 6)) ^ (b & 0xFF);
    unit=_array[nodePos];
    if ((unit & ((1 << 31) | 0xFF)) != (b & 0xff)) {
      return -1;
    }
  }
  if (((unit >>> 8) & 1) != 1) {
    return -1;
  }
  unit=_array[nodePos ^ ((unit >>> 10) << ((unit & (1 << 9)) >>> 6))];
  return unit & ((1 << 31) - 1);
}
