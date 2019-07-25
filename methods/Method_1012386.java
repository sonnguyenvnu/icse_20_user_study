public void apply(){
  if (view != null) {
    SharedPreferences sharedPreferences=view.getContext().getSharedPreferences(ThemeUtil.NAME,Context.MODE_PRIVATE);
    boolean isTheme=sharedPreferences.getBoolean(ThemeUtil.IS_THEME,false);
    int value=sharedPreferences.getInt(ThemeUtil.THEME_COLOR,Color.parseColor("#FF2F3A4C"));
    for (    SkinAttr skinAttr : skinAttrs) {
      if (isTheme) {
        if (skinAttr.getAttrName().equals("textColor")) {
          ((TextView)view).setTextColor(value);
        }
 else         if (skinAttr.getAttrName().equals("background")) {
          if (view instanceof SeekBar) {
            continue;
          }
          view.setBackgroundColor(value);
        }
 else         if (skinAttr.getAttrName().equals("thumb") && view instanceof SeekBar) {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CommonLogger.e("??????");
            ((SeekBar)view).setThumb(view.getContext().getResources().getDrawable(R.drawable.thumb_normal,view.getContext().getTheme()));
          }
 else {
            CommonLogger.e("??????1");
            ((SeekBar)view).setThumb(view.getContext().getResources().getDrawable(R.drawable.thumb_normal));
          }
        }
      }
 else {
        skinAttr.apply(view);
      }
    }
  }
}
