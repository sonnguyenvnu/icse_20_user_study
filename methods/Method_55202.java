/** 
 * Creates an instance of a class, allocating memory for the class in the default malloc memory zone.
 * @param cls        the class that you want to allocate an instance of
 * @param extraBytes an integer indicating the number of extra bytes to allocate. The additional bytes can be used to store additional instance variables beyond thosedefined in the class definition.
 * @return an instance of the class {@code cls}
 */
@NativeType("id") public static long class_createInstance(@NativeType("Class") long cls,@NativeType("size_t") long extraBytes){
  long __functionAddress=Functions.class_createInstance;
  if (CHECKS) {
    check(cls);
  }
  return invokePPP(cls,extraBytes,__functionAddress);
}
