/** 
 * Releases a Core Foundation object. <p>If the retain count of  {@code cf} becomes zero the memory allocated to the object is deallocated and the object is destroyed. If you create, copy, orexplicitly retain (see the  {@link #CFRetain} function) a Core Foundation object, you are responsible for releasing it when you no longer need it.</p>
 * @param cf the CFType object to release
 */
public static void CFRelease(@NativeType("CFTypeRef") long cf){
  if (CHECKS) {
    check(cf);
  }
  nCFRelease(cf);
}
