/** 
 * N.B. Thread safety. If you call isValid from a different thread than  {@link #resetNative()}then be sure to do so while synchronizing on the hybrid. For example: <pre><code> synchronized(hybrid) { if (hybrid.isValid) { // Do stuff. } } </code></pre>
 */
public boolean isValid(){
  return mDestructor.mNativePointer != 0;
}
