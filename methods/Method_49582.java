/** 
 * This method generates the second argument to {@link HBaseAdmin#createTable(HTableDescriptor,byte[],byte[],int)}. <p/> From the  {@code createTable} javadoc:"The start key specified will become the end key of the first region of the table, and the end key specified will become the start key of the last region of the table (the first region has a null start key and the last region has a null end key)" <p/> To summarize, the  {@code createTable} argument called "startKey" isactually the end key of the first region.
 */
private byte[] getStartKey(int regionCount){
  ByteBuffer regionWidth=ByteBuffer.allocate(4);
  regionWidth.putInt((int)(((1L << 32) - 1L) / regionCount)).flip();
  return StaticArrayBuffer.of(regionWidth).getBytes(0,4);
}
