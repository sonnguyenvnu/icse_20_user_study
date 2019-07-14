/** 
 * ??????UniqueName?????
 * @param uniqueName ??????
 * @return ???
 */
public static String getInterfaceName(String uniqueName){
  if (StringUtils.isEmpty(uniqueName)) {
    return uniqueName;
  }
  int index=uniqueName.indexOf(':');
  return index < 0 ? uniqueName : uniqueName.substring(0,index);
}
