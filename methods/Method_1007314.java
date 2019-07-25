/** 
 * Obtains the URL of the specified class file.
 * @return null if the class file could not be found.
 */
@Override public URL find(String classname){
  String filename='/' + classname.replace('.','/') + ".class";
  return thisClass.getResource(filename);
}
