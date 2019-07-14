/** 
 * Simple factory for  {@link File} objects but with home resolving.
 */
public static File file(String fileName){
  fileName=StringUtil.replace(fileName,USER_HOME,SystemUtil.info().getHomeDir());
  return new File(fileName);
}
