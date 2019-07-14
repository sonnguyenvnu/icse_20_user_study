/** 
 * ??byte???4bit???
 * @param high4 ?4? &lt;16
 * @param low4  ?4? &lt;16
 * @return ??byte???4bit???
 */
public static byte buildHigh4Low4Bytes(byte high4,byte low4){
  return (byte)((high4 << 4) + low4);
}
