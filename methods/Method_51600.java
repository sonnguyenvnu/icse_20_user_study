/** 
 * Returns the abbreviated name of the type, without the package name
 * @param fullTypeName
 * @return String
 */
public static String withoutPackageName(String fullTypeName){
  int dotPos=fullTypeName.lastIndexOf('.');
  return dotPos > 0 ? fullTypeName.substring(dotPos + 1) : fullTypeName;
}
