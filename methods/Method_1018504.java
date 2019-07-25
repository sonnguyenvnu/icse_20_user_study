/** 
 * Flushes the pending buffer into the given output array.  If the output array is to small, only a partial flush is done.
 * @param output the output array;
 * @param offset the offset into output array;
 * @param length the maximum number of bytes to store;
 * @exception IndexOutOfBoundsException if offset or length areinvalid.
 */
public final int flush(byte[] output,int offset,int length){
  if (bitCount >= 8) {
    buf[end++]=(byte)bits;
    bits>>>=8;
    bitCount-=8;
  }
  if (length > end - start) {
    length=end - start;
    System.arraycopy(buf,start,output,offset,length);
    start=0;
    end=0;
  }
 else {
    System.arraycopy(buf,start,output,offset,length);
    start+=length;
  }
  return length;
}
