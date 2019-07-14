/** 
 * @return the mapping from default endpoint paths to custom ones (or the default if no customization is known)
 */
public String getPath(String defaultPath){
  String result=defaultPath;
  if (mappings.containsKey(defaultPath)) {
    result=mappings.get(defaultPath);
  }
  return result;
}
