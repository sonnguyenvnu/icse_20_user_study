/** 
 * Puts two bytes into this byte vector. The byte vector is automatically enlarged if necessary.
 * @param b1 a byte.
 * @param b2 another byte.
 * @return this byte vector.
 */
ByteVector put11(final int b1,final int b2){
  int length=this.length;
  if (length + 2 > data.length) {
    enlarge(2);
  }
  final byte[] data=this.data;
  data[length++]=(byte)b1;
  data[length++]=(byte)b2;
  this.length=length;
  return this;
}
