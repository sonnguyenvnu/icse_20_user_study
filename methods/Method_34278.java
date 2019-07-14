/** 
 * @return the mapping from default endpoint paths to custom ones (or the default if no customization is known)
 */
public String getServletPath(String defaultPath){
  return (prefix == null ? "" : prefix) + getPath(defaultPath);
}
