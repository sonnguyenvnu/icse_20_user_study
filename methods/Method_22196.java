/** 
 * @return last created activity, if any
 */
@Nullable public Activity getLastActivity(){
  return activityStack.peek();
}
