/** 
 * byte[] ? char[]  ?10???
 * @param byteArr byte??
 * @return char??
 */
public static char[] byteArr2CharArr(@NotNull byte[] byteArr){
  Charset cs=Charset.forName("UTF-8");
  ByteBuffer bb=ByteBuffer.allocate(byteArr.length);
  bb.put(byteArr);
  bb.flip();
  CharBuffer cb=cs.decode(bb);
  return cb.array();
}
