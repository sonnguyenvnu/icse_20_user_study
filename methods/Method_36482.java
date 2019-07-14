/** 
 * Create extension point builder
 * @param name extension point name
 * @param beanClassLoader classloader
 * @return extension point builder
 */
public static ExtensionPointBuilder genericExtensionPoint(String name,ClassLoader beanClassLoader){
  return genericExtensionPoint(name,null,beanClassLoader);
}
