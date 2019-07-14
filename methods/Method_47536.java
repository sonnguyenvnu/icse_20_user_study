public static Typeface getFontAwesome(Context context){
  if (fontAwesome == null)   fontAwesome=Typeface.createFromAsset(context.getAssets(),"fontawesome-webfont.ttf");
  return fontAwesome;
}
