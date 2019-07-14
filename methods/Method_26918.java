@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1) private static void sceneChangeRunTransition(final @Nullable ViewGroup sceneRoot,final @Nullable Transition transition){
  if (transition != null && sceneRoot != null && isTransitionsAllowed()) {
    ViewGroupOverlayUtils.initializeOverlay(sceneRoot);
    MultiListener listener=new MultiListener(transition,sceneRoot);
    sceneRoot.addOnAttachStateChangeListener(listener);
    sceneRoot.getViewTreeObserver().addOnPreDrawListener(listener);
  }
 else {
    sPendingTransitions.remove(sceneRoot);
  }
}
