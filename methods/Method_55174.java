/** 
 * Returns the version number of a class definition. <p>You can use the version number of the class definition to provide versioning of the interface that your class represents to other classes. This is especially useful for object serialization (that is, archiving of the object in a flattened form), where it is important to recognize changes to the layout of the instance variables in different class-definition versions.</p> <p>Classes derived from the Foundation framework NSObject class can obtain the class-definition version number using the getVersion class method, which is implemented using the class_getVersion function.</p>
 * @param cls a pointer to an Class data structure. Pass the class definition for which you wish to obtain the version
 * @return an integer indicating the version number of the class definition
 */
public static int class_getVersion(@NativeType("Class") long cls){
  long __functionAddress=Functions.class_getVersion;
  if (CHECKS) {
    check(cls);
  }
  return invokePI(cls,__functionAddress);
}
