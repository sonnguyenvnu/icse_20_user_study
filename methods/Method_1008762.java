/** 
 * Finds the  {@link URL} of the resource
 * @param packageName the base package name to find the resource
 * @param resourceFileName the resource file name relative to the package folder
 * @return the URL of the specified resource
 */
public static URL find(ClassLoader classLoader,String packageName,String resourceFileName){
  String packagePath=packagePath(packageName);
  String resourcePath=packagePath + resourceFileName;
  if (!resourcePath.startsWith("/"))   resourcePath="/" + resourcePath;
  return classLoader.getResource(resourcePath);
}
