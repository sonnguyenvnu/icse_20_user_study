/** 
 * Obtains the URL of the specified class file. This method calls <code>getResource(String)</code> on the class loader.
 * @return null if the class file could not be found. 
 */
@Override public URL find(String classname){
  String cname=classname.replace('.','/') + ".class";
  ClassLoader cl=clref.get();
  if (cl == null)   return null;
  URL url=cl.getResource(cname);
  return url;
}
