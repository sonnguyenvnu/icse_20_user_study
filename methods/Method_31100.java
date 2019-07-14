public static void removeOnPreDraw(@NonNull View view,@NonNull Runnable runnable){
  view.getViewTreeObserver().removeOnPreDrawListener(new OnPreDrawListenerRunnableWrapper(view,runnable));
}
