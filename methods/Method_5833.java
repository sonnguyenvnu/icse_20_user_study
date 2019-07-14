/** 
 * Throws  {@link IllegalStateException} if the calling thread is not the application's mainthread.
 * @throws IllegalStateException If the calling thread is not the application's main thread.
 */
public static void checkMainThread(){
  if (ExoPlayerLibraryInfo.ASSERTIONS_ENABLED && Looper.myLooper() != Looper.getMainLooper()) {
    throw new IllegalStateException("Not in applications main thread");
  }
}
