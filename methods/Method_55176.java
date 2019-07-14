/** 
 * Returns the size of instances of a class.
 * @param cls a class object
 * @return the size in bytes of instances of the class {@code cls}, or 0 if  {@code cls} is Nil
 */
@NativeType("size_t") public static long class_getInstanceSize(@NativeType("Class") long cls){
  long __functionAddress=Functions.class_getInstanceSize;
  return invokePP(cls,__functionAddress);
}
