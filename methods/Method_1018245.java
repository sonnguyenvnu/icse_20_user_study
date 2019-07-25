/** 
 * Read Huffman encoded characters into a keep.
 * @param huff A Huffman decoder.
 * @param ext A Huffman decoder for the extended bytes.
 * @param keep The keep that will receive the kim.
 * @return The string that was read.
 * @throws JSONException
 */
private String read(Huff huff,Huff ext,Keep keep) throws JSONException {
  Kim kim;
  int at=0;
  int allocation=256;
  byte[] bytes=new byte[allocation];
  if (bit()) {
    return getAndTick(keep,this.bitreader).toString();
  }
  while (true) {
    if (at >= allocation) {
      allocation*=2;
      bytes=java.util.Arrays.copyOf(bytes,allocation);
    }
    int c=huff.read(this.bitreader);
    if (c == end) {
      break;
    }
    while ((c & 128) == 128) {
      bytes[at]=(byte)c;
      at+=1;
      c=ext.read(this.bitreader);
    }
    bytes[at]=(byte)c;
    at+=1;
  }
  if (at == 0) {
    return "";
  }
  kim=new Kim(bytes,at);
  keep.register(kim);
  return kim.toString();
}
