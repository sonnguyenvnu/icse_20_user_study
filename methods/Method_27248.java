@ColorInt private static int getColorAttr(@NonNull Context context,int attr){
  Resources.Theme theme=context.getTheme();
  TypedArray typedArray=theme.obtainStyledAttributes(new int[]{attr});
  final int color=typedArray.getColor(0,Color.LTGRAY);
  typedArray.recycle();
  return color;
}
