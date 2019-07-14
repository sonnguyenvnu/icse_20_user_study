/** 
 * Copy "len" bytes from "src" to "op", one byte at a time.  Used for handling COPY operations where the input and output regions may overlap.  For example, suppose: src    == "ab" op     == src + 2 len    == 20 <p> After incrementalCopy, the result will have eleven copies of "ab" ababababababababababab Note that this does not match the semantics of either memcpy() or memmove().
 */
private static void incrementalCopy(byte[] src,int srcIndex,byte[] op,int opIndex,int length){
  do {
    op[opIndex++]=src[srcIndex++];
  }
 while (--length > 0);
}
