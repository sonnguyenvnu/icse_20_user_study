/** 
 * String????? byte ?? ???utf-8????
 * @param data String???
 * @return byte ?????DataUtil.stringHex2ByteArr("?");
 */
public static byte[] string2ByteArr(@NotNull String data){
  try {
    return string2ByteArr(data,null);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
