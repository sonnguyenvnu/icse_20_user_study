@TargetApi(Build.VERSION_CODES.LOLLIPOP) private static void postponeTransitionUntilDecorViewPreDraw(final Activity activity){
  if (!shouldEnableTransition()) {
    return;
  }
  activity.postponeEnterTransition();
  ViewUtils.postOnPreDraw(activity.getWindow().getDecorView(),new Runnable(){
    @Override public void run(){
      activity.startPostponedEnterTransition();
    }
  }
);
}
