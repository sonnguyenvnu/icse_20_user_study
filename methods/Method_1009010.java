/** 
 * convert a single long array into an array of SmallDocumentBlock instances
 * @param array the byte array to be converted
 * @param size the intended size of the array (which may be smaller)
 * @return an array of SmallDocumentBlock instances, filled fromthe array
 */
public static SmallDocumentBlock[] convert(POIFSBigBlockSize bigBlockSize,byte[] array,int size){
  SmallDocumentBlock[] rval=new SmallDocumentBlock[(size + _block_size - 1) / _block_size];
  int offset=0;
  for (int k=0; k < rval.length; k++) {
    rval[k]=new SmallDocumentBlock(bigBlockSize);
    if (offset < array.length) {
      int length=Math.min(_block_size,array.length - offset);
      System.arraycopy(array,offset,rval[k]._data,0,length);
      if (length != _block_size) {
        Arrays.fill(rval[k]._data,length,_block_size,_default_fill);
      }
    }
 else {
      Arrays.fill(rval[k]._data,_default_fill);
    }
    offset+=_block_size;
  }
  return rval;
}
