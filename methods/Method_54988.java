/** 
 * Calls the function specified by  {@code funcptr} with the arguments bound to the {@code CallVM} and returns.<p>After the invocation of the foreign function call, the argument values are still bound and a second call using the same arguments can be issued. If you need to clear the argument bindings, you have to reset the  {@code CallVM}.</p>
 * @param vm      a {@code CallVM} instance
 * @param funcptr the function pointer
 */
@NativeType("DClonglong") public static long dcCallLongLong(@NativeType("DCCallVM *") long vm,@NativeType("DCpointer") long funcptr){
  if (CHECKS) {
    check(vm);
    check(funcptr);
  }
  return ndcCallLongLong(vm,funcptr);
}
