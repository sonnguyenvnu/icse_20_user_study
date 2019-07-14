public static Drawable resolveDrawable(Context context,@AttrRes int attr,int fallback){
  TypedArray a=context.getTheme().obtainStyledAttributes(new int[]{attr});
  Drawable drawable=null;
  if (fallback != 0) {
    drawable=ContextCompat.getDrawable(context,fallback).mutate();
  }
  try {
    Drawable d=a.getDrawable(0);
    if (d != null) {
      drawable=d;
    }
  }
  finally {
    a.recycle();
  }
  return drawable;
}
