/** 
 * Takes intent data from the view.
 */
public void intent(final @NonNull Intent intent){
  this.intent.onNext(intent);
}
