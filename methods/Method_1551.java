public static void initialize(BitmapCounterConfig bitmapCounterConfig){
  if (sBitmapCounter != null) {
    throw new IllegalStateException("BitmapCounter has already been created! `BitmapCounterProvider.initialize(...)` should only be called before `BitmapCounterProvider.get()` or not at all!");
  }
 else {
    sMaxBitmapCount=bitmapCounterConfig.getMaxBitmapCount();
  }
}
