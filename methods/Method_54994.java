/** 
 * (Re)initializes a callback object.
 * @param pcb       the callback object
 * @param signature the function signature of the function to mimic
 * @param handler   a pointer to a callback handler
 * @param userdata  a pointer to custom data that might be useful in the handler
 */
public static void dcbInitCallback(@NativeType("DCCallback *") long pcb,@NativeType("char const *") ByteBuffer signature,@NativeType("DCCallbackHandler *") long handler,@NativeType("void *") long userdata){
  if (CHECKS) {
    check(pcb);
    checkNT1(signature);
    check(handler);
    check(userdata);
  }
  ndcbInitCallback(pcb,memAddress(signature),handler,userdata);
}
