/** 
 * ??byte???2bit?6bit???
 * @param high2 ?2? &lt;4
 * @param low6  ?6? &lt;64
 * @return byte??{&lt;4,&lt;64}
 */
public static byte buildHigh2Low6Bytes(byte high2,byte low6){
  return (byte)((high2 << 6) + low6);
}
