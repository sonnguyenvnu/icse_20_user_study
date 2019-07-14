/** 
 * Tests whether there exists a NAL start code at a given index.
 * @param data The data.
 * @param index The index to test.
 * @return Whether there exists a start code that begins at {@code index}.
 */
private static boolean isNalStartCode(byte[] data,int index){
  if (data.length - index <= NAL_START_CODE.length) {
    return false;
  }
  for (int j=0; j < NAL_START_CODE.length; j++) {
    if (data[index + j] != NAL_START_CODE[j]) {
      return false;
    }
  }
  return true;
}
