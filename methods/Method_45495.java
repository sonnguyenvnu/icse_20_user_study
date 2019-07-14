/** 
 * ???????
 * @return ?????
 */
@VisibleForTesting static boolean[] parseOSName(){
  boolean[] result=new boolean[]{false,false,false};
  String osName=System.getProperty("os.name").toLowerCase();
  if (osName.contains("windows")) {
    result[0]=true;
  }
 else   if (osName.contains("linux")) {
    result[1]=true;
  }
 else   if (osName.contains("mac")) {
    result[2]=true;
  }
  return result;
}
