/** 
 * ??????????????????jar?
 * @param cls the cls
 * @return the code base
 */
public static String getCodeBase(Class<?> cls){
  if (cls == null) {
    return null;
  }
  ProtectionDomain domain=cls.getProtectionDomain();
  if (domain == null) {
    return null;
  }
  CodeSource source=domain.getCodeSource();
  if (source == null) {
    return null;
  }
  URL location=source.getLocation();
  if (location == null) {
    return null;
  }
  return location.getFile();
}
