/** 
 * Returns the position of the first TS_SYNC_BYTE within the range [startPosition, limitPosition) from the provided data array, or returns limitPosition if sync byte could not be found.
 */
public static int findSyncBytePosition(byte[] data,int startPosition,int limitPosition){
  int position=startPosition;
  while (position < limitPosition && data[position] != TsExtractor.TS_SYNC_BYTE) {
    position++;
  }
  return position;
}
