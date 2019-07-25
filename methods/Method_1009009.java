/** 
 * convert a single long array into an array of DocumentBlock instances
 * @param array the byte array to be converted
 * @param size the intended size of the array (which may be smaller)
 * @return an array of DocumentBlock instances, filled from theinput array
 */
public static DocumentBlock[] convert(final POIFSBigBlockSize bigBlockSize,final byte[] array,final int size){
  DocumentBlock[] rval=new DocumentBlock[(size + bigBlockSize.getBigBlockSize() - 1) / bigBlockSize.getBigBlockSize()];
  int offset=0;
  for (int k=0; k < rval.length; k++) {
    rval[k]=new DocumentBlock(bigBlockSize);
    if (offset < array.length) {
      int length=Math.min(bigBlockSize.getBigBlockSize(),array.length - offset);
      System.arraycopy(array,offset,rval[k]._data,0,length);
      if (length != bigBlockSize.getBigBlockSize()) {
        Arrays.fill(rval[k]._data,length,bigBlockSize.getBigBlockSize(),_default_value);
      }
    }
 else {
      Arrays.fill(rval[k]._data,_default_value);
    }
    offset+=bigBlockSize.getBigBlockSize();
  }
  return rval;
}
