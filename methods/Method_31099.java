public static void postOnPreDraw(@NonNull View view,@NonNull Runnable runnable){
  view.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListenerRunnableWrapper(view,runnable));
}
