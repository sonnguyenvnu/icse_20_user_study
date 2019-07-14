@TargetApi(Build.VERSION_CODES.LOLLIPOP) public static void setupTransitionAfterSetContentView(Activity activity){
  if (!shouldEnableTransition()) {
    return;
  }
  setupTransitionForAppBar(activity);
  postponeTransitionUntilDecorViewPreDraw(activity);
}
