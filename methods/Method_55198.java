/** 
 * Unsafe version of:  {@link #class_addIvar} 
 */
public static boolean nclass_addIvar(long cls,long name,long size,byte alignment,long types){
  long __functionAddress=Functions.class_addIvar;
  if (CHECKS) {
    check(cls);
  }
  return invokePPPPZ(cls,name,size,alignment,types,__functionAddress);
}
