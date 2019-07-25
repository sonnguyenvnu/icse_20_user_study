/** 
 * Init utils. <p>Init it in the class of Application.</p>
 * @param app application
 */
public static void init(@NonNull final Application app){
  if (sApplication == null) {
    Utils.sApplication=app;
    Utils.sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
  }
}
