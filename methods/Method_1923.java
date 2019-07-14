/** 
 * Wraps the given animation backend with an activity check. When no frame has been drawn for more than 2 seconds, an inactivity toast message will be displayed.
 * @param context the context to be used for displaying the toast message
 * @param animationBackend the backend to wrap with the inactivity check
 * @return the wrapped backend to use
 */
public static AnimationBackend wrapAnimationBackendWithInactivityCheck(final Context context,final AnimationBackend animationBackend){
  AnimationBackendDelegateWithInactivityCheck.InactivityListener inactivityListener=new AnimationBackendDelegateWithInactivityCheck.InactivityListener(){
    @Override public void onInactive(){
      if (animationBackend instanceof AnimationBackendDelegateWithInactivityCheck.InactivityListener) {
        ((AnimationBackendDelegateWithInactivityCheck.InactivityListener)animationBackend).onInactive();
      }
      Toast.makeText(context,"Animation backend inactive.",Toast.LENGTH_SHORT).show();
    }
  }
;
  return createForBackend(animationBackend,inactivityListener,RealtimeSinceBootClock.get(),UiThreadImmediateExecutorService.getInstance());
}
