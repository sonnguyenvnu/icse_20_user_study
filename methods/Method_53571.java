/** 
 * 16???String??? ???  byte ??
 * @param data 16???String???
 * @return byte ?????DataUtil.stringHex2ByteArr("e4bda0e5a5bd");
 */
public static byte[] stringHex2ByteArr(@NotNull String data){
  try {
    if (isHexadecimal(data) != true) {
      throw new IllegalAccessException("??????????????????? string2ByteArr(String)????");
    }
    int len=data.length();
    byte[] d=new byte[len / 2];
    for (int i=0; i < len; i+=2) {
      d[i / 2]=(byte)((Character.digit(data.charAt(i),16) << 4) + Character.digit(data.charAt(i + 1),16));
    }
    return d;
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
