private static int convert(Context context,int amount,int conversionUnit){
  if (amount < 0) {
    throw new IllegalArgumentException("px should not be less than zero");
  }
  Resources r=context.getResources();
  return (int)TypedValue.applyDimension(conversionUnit,amount,r.getDisplayMetrics());
}
