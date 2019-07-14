/** 
 * This should be called exactly once at app startup, before any Litho work happens. 
 */
public static void provide(Systrace instance){
  sInstance=instance;
}
