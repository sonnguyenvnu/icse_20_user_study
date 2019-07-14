/** 
 * Translates  {@code len} number of Unicode characters beginning at offset start into modified UTF-8 encoding and place the result in the given buffer{@code buf}.
 */
public static void GetStringUTFRegion(@NativeType("jstring") String str,@NativeType("jsize") int start,@NativeType("jsize") int len,@NativeType("char *") ByteBuffer buf){
  if (CHECKS) {
    check(buf,len);
  }
  nGetStringUTFRegion(str,start,len,memAddress(buf));
}
