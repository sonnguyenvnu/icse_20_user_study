/** 
 * Retains a Core Foundation object. <p>You should retain a Core Foundation object when you receive it from elsewhere (that is, you did not create or copy it) and you want it to persist. If you retain a Core Foundation object you are responsible for releasing it.</p>
 * @param cf the CFType object to retain
 */
@NativeType("CFTypeRef") public static long CFRetain(@NativeType("CFTypeRef") long cf){
  if (CHECKS) {
    check(cf);
  }
  return nCFRetain(cf);
}
