/** 
 * Unsafe version of  {@link #next(AIExportDataBlob) next}. 
 */
public static void nnext(long struct,@Nullable AIExportDataBlob value){
  memPutAddress(struct + AIExportDataBlob.NEXT,memAddressSafe(value));
}
