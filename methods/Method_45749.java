/** 
 * ??USER_HOME??
 * @param base ????????
 * @return ??????
 */
public static String getUserHomeDir(String base){
  String userhome=System.getProperty("user.home");
  File file=new File(userhome,base);
  if (file.exists()) {
    if (!file.isDirectory()) {
      throw new SofaRpcRuntimeException(file.getAbsolutePath() + " exists, but not directory");
    }
  }
 else {
    file.mkdirs();
  }
  return file.getAbsolutePath();
}
