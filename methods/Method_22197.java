/** 
 * @return a list of activities in the current process
 */
@NonNull public List<Activity> getLastActivities(){
  return new ArrayList<>(activityStack);
}
