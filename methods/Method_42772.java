/** 
 * ????
 * @param loginPwd ????
 * @param salt
 * @return
 */
public static String getPwd(String loginPwd,String salt){
  String newPassword=new SimpleHash(algorithmName,loginPwd,ByteSource.Util.bytes(salt),hashIterations).toHex();
  return newPassword;
}
