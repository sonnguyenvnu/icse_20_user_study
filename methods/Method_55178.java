/** 
 * Returns the Ivar for a specified instance variable of a given class.
 * @param cls  the class whose instance variable you wish to obtain
 * @param name the name of the instance variable definition to obtain
 * @return a pointer to an Ivar data structure containing information about the instance variable specified by name
 */
@NativeType("Ivar") public static long class_getInstanceVariable(@NativeType("Class") long cls,@NativeType("char const *") ByteBuffer name){
  if (CHECKS) {
    checkNT1(name);
  }
  return nclass_getInstanceVariable(cls,memAddress(name));
}
