/** 
 * This method converts a HEX string to Byte[]
 * @param hex: the HEX string
 * @return : a byte array
 */
private static byte[] hexStr2Bytes(String hex){
  byte[] bArray=new BigInteger("10" + hex,16).toByteArray();
  byte[] ret=new byte[bArray.length - 1];
  for (int i=0; i < ret.length; i++)   ret[i]=bArray[i + 1];
  return ret;
}
