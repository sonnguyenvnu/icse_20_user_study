/** 
 * Sets the specified value to the  {@code size} field of the specified {@code struct}. 
 */
public static void nsize(long struct,long value){
  memPutAddress(struct + AIExportDataBlob.SIZE,value);
}
