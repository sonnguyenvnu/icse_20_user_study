/** 
 * Unsafe version of  {@link #mType}. 
 */
public static int nmType(long struct){
  return UNSAFE.getInt(null,struct + AIMetaDataEntry.MTYPE);
}
