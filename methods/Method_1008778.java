/** 
 * Show the view based on the configuration Reveal is available only for Lollipop and above in other only fadein will work To support reveal in older versions use github.com/ozodrukh/CircularReveal
 * @param activity
 */
private void show(final Activity activity){
  if (preferencesManager.isDisplayed(usageId))   return;
  ((ViewGroup)activity.getWindow().getDecorView()).addView(this);
  setReady(true);
  handler.postDelayed(new Runnable(){
    @Override public void run(){
      try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          if (isRevealAnimationEnabled)           startRevealAnimation(activity);
 else {
            startFadinAnimation(activity);
          }
        }
 else {
          startFadinAnimation(activity);
        }
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
,100);
}
