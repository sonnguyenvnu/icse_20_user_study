/** 
 * Unsafe version of  {@link #name(AIString) name}. 
 */
public static void nname(long struct,AIString value){
  memCopy(value.address(),struct + AIExportDataBlob.NAME,AIString.SIZEOF);
}
