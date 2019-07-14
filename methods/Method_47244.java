int resolveColor(@NonNull Context context,@AttrRes int i){
  TypedArray obtainStyledAttributes=context.obtainStyledAttributes(new int[]{i});
  int color=obtainStyledAttributes.getColor(0,0);
  obtainStyledAttributes.recycle();
  return color;
}
