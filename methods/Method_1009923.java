/** 
 * ???
 * @param application App ? Application
 * @param isDebug     ???debug??
 */
public static void init(@NonNull Application application,boolean isDebug,@Nullable String defaultScheme){
  if (application == null) {
    throw new NullPointerException("the Application is null");
  }
  Component.application=application;
  Component.isDebug=isDebug;
  if (defaultScheme != null && !defaultScheme.isEmpty()) {
    Component.defaultScheme=defaultScheme;
  }
  application.registerActivityLifecycleCallbacks(new ComponentLifecycleCallback());
}
