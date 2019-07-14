/** 
 * ????????key???, ????[] -> []/i
 * @param parentPath
 * @param valuePath
 * @return
 */
public static String getValuePath(String parentPath,String valuePath){
  if (valuePath.startsWith("/")) {
    valuePath=getAbsPath(parentPath,valuePath);
  }
 else {
    valuePath=replaceArrayChildPath(parentPath,valuePath);
  }
  return valuePath;
}
