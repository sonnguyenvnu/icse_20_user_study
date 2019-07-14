@ColorInt public static int getWindowBackground(@PrefGetter.ThemeType int theme){
  if (theme == PrefGetter.AMLOD) {
    return Color.parseColor("#0B162A");
  }
 else   if (theme == PrefGetter.BLUISH) {
    return Color.parseColor("#111C2C");
  }
 else   if (theme == PrefGetter.DARK) {
    return Color.parseColor("#22252A");
  }
 else {
    return Color.parseColor("#EEEEEE");
  }
}
