/** 
 * Unsafe version of  {@link #mMaintainerString}. 
 */
public static String nmMaintainerString(long struct){
  return memUTF8(memGetAddress(struct + AIImporterDesc.MMAINTAINER));
}
