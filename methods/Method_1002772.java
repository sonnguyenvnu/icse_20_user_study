/** 
 * Invokes the specified  {@link Runnable} with {@link BouncyCastleKeyFactoryProvider} enabled temporarily.
 */
public static void call(Runnable task){
  try {
    call(() -> {
      task.run();
      return true;
    }
);
  }
 catch (  Exception e) {
    Exceptions.throwUnsafely(e);
  }
}
