/** 
 * Changes the value of an instance variable of a class instance.
 * @param obj   a pointer to an instance of a class. Pass the object containing the instance variable whose value you wish to modify
 * @param name  a C string. Pass the name of the instance variable whose value you wish to modify
 * @param value the new value for the instance variable
 * @return a pointer to the Ivar data structure that defines the type and name of the instance variable specified by name
 */
@NativeType("Ivar") public static long object_setInstanceVariable(@NativeType("id") long obj,@NativeType("char const *") ByteBuffer name,@NativeType("void *") ByteBuffer value){
  if (CHECKS) {
    checkNT1(name);
  }
  return nobject_setInstanceVariable(obj,memAddress(name),memAddress(value));
}
