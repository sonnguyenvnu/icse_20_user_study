/** 
 * Unsafe version of  {@link #name}. 
 */
public static AIString nname(long struct){
  return AIString.create(struct + AIExportDataBlob.NAME);
}
