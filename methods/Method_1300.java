/** 
 * If given context is an instance of ListenableActivity then creates new instance of WeakReferenceActivityListenerAdapter and adds it to activity's listeners
 */
public static void register(ActivityListener activityListener,Context context){
  ListenableActivity activity=getListenableActivity(context);
  if (activity != null) {
    Listener listener=new Listener(activityListener);
    activity.addActivityListener(listener);
  }
}
