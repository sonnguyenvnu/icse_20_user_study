/** 
 * Cycically extract a word of key material.
 * @param data the string to extract the data from
 * @param offp a "pointer" (as a one-entry array) to thecurrent offset into data
 * @return the next word of material from data
 */
private static int streamtoword(byte[] data,int[] offp){
  int i;
  int word=0;
  int off=offp[0];
  for (i=0; i < 4; i++) {
    word=(word << 8) | (data[off] & 0xff);
    off=(off + 1) % data.length;
  }
  offp[0]=off;
  return word;
}
