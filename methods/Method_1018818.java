/** 
 * ?????????Activity
 */
public void clear(boolean needFinish){
  Iterator<Activity> iterator=mActivities.iterator();
  while (iterator.hasNext()) {
    Activity activity=iterator.next();
    if (activity != null && needFinish && !ContextUtils.isFinished(activity)) {
      activity.finish();
    }
    iterator.remove();
  }
}
