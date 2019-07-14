/** 
 * Unsafe version of  {@link #mValues}. 
 */
public static AIMetaDataEntry.Buffer nmValues(long struct){
  return AIMetaDataEntry.create(memGetAddress(struct + AIMetaData.MVALUES),nmNumProperties(struct));
}
