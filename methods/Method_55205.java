/** 
 * Destroys an instance of a class without freeing memory and removes any of its associated references. <p>This method does nothing if obj is  {@link #nil}.</p> <h5>Important</h5> <p>The garbage collector does not call this function. As a result, if you edit this function, you should also edit finalize. That said, Core Foundation and other clients do call this function under garbage collection.</p>
 * @param obj the instance to destroy
 */
@NativeType("void *") public static long objc_destructInstance(@NativeType("id") long obj){
  long __functionAddress=Functions.objc_destructInstance;
  if (CHECKS) {
    check(obj);
  }
  return invokePP(obj,__functionAddress);
}
