/** 
 * ??
 */
public void exit(){
  for (  Activity activity : activityList) {
    if (!activity.isFinishing() && activity != null) {
      activity.finish();
    }
  }
}
