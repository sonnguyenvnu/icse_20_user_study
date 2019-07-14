/** 
 * Gets the qualified name of a class.
 * @param clazz Class object
 * @return The qualified name of the class, or null if the class is null
 */
public static JavaTypeQualifiedName ofClass(Class<?> clazz){
  if (clazz == null) {
    return null;
  }
  String name=clazz.getName();
  if (name.indexOf('.') < 0) {
    name='.' + name;
  }
  return (JavaTypeQualifiedName)ofStringWithClassLoader(name,clazz.getClassLoader());
}
