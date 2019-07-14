/** 
 * A new PMDASMClassLoader is created for each compilation unit, this method allows to reuse the same PMDASMClassLoader across all the compilation units.
 */
public static synchronized PMDASMClassLoader getInstance(ClassLoader parent){
  if (parent.equals(cachedClassLoader)) {
    return cachedPMDASMClassLoader;
  }
  cachedClassLoader=parent;
  cachedPMDASMClassLoader=new PMDASMClassLoader(parent);
  return cachedPMDASMClassLoader;
}
