public static void postOnPreDraw(@NonNull View view,@NonNull BooleanSupplier runnable){
  view.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListenerBooleanSupplierWrapper(view,runnable));
}
