/** 
 * Unsafe version of  {@link #mValues(AIMetaDataEntry.Buffer) mValues}. 
 */
public static void nmValues(long struct,AIMetaDataEntry.Buffer value){
  memPutAddress(struct + AIMetaData.MVALUES,value.address());
}
