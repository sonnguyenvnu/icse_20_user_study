/** 
 * Disassociates a block from an IMP that was created using  {@link #imp_implementationWithBlock}, and releases the copy of the block that was created.
 * @param anImp an IMP that was created using the {@link #imp_implementationWithBlock} function.
 * @return {@link #YES} if the block was released successfully; otherwise, {@link #NO} (for example, the function returns {@link #NO} if the block was not used to create {@code anImp}previously).
 */
@NativeType("BOOL") public static boolean imp_removeBlock(@NativeType("IMP") long anImp){
  long __functionAddress=Functions.imp_removeBlock;
  if (CHECKS) {
    check(anImp);
  }
  return invokePZ(anImp,__functionAddress);
}
