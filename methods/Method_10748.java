/** 
 * DES??
 * @param data ??
 * @param key  8????
 * @return ??
 */
public static byte[] decryptDES(byte[] data,byte[] key){
  return DESTemplet(data,key,DES_Algorithm,DES_Transformation,false);
}
