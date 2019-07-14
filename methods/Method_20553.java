/** 
 * Takes activity result data from the activity.
 */
public void activityResult(final @NonNull ActivityResult activityResult){
  this.activityResult.onNext(activityResult);
}
