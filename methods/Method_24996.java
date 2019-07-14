/** 
 * Deduce body length in bytes. Either from "content-length" header or read bytes.
 */
public long getBodySize(){
  if (this.headers.containsKey("content-length")) {
    return Long.parseLong(this.headers.get("content-length"));
  }
 else   if (this.splitbyte < this.rlen) {
    return this.rlen - this.splitbyte;
  }
  return 0;
}
