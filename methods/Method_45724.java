/** 
 * hex string to byte[], such as "0001" -> [0,1]
 * @param str hex string
 * @return byte[]
 */
public static byte[] hex2byte(String str){
  byte[] bytes=str.getBytes();
  if ((bytes.length % 2) != 0) {
    throw new IllegalArgumentException();
  }
  byte[] b2=new byte[bytes.length / 2];
  for (int n=0; n < bytes.length; n+=2) {
    String item=new String(bytes,n,2);
    b2[n / 2]=(byte)Integer.parseInt(item,16);
  }
  return b2;
}
