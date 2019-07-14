public static void animateTo(Window window,int color,int duration){
  if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
    return;
  }
  ObjectAnimator animator=ObjectAnimator.ofInt(window,"statusBarColor",window.getStatusBarColor(),color);
  animator.setEvaluator(sArgbEvaluator);
  animator.setDuration(duration);
  animator.setAutoCancel(true);
  animator.start();
}
