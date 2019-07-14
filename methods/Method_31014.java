public static void setup(@NonNull Application application){
  syncDefaultNightMode();
  application.registerActivityLifecycleCallbacks(sActivityHelper);
}
