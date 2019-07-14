/** 
 * Copies  {@code len} number of Unicode characters beginning at offset {@code start} to the given buffer {@code buf}. 
 */
public static void GetStringRegion(@NativeType("jstring") String str,@NativeType("jsize") int start,@NativeType("jchar *") ByteBuffer buf){
  nGetStringRegion(str,start,buf.remaining() >> 1,memAddress(buf));
}
