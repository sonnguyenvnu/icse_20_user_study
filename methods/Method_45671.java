/** 
 * ???????ClassLoader
 * @param appName     ???
 * @param classloader ????ClassLoader
 */
public static void registerAppClassLoader(String appName,ClassLoader classloader){
  APPNAME_CLASSLOADER_MAP.put(appName,classloader);
}
