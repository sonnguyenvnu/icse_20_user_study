void changeFontSize(Context context,boolean increase){
  mFontSize+=(increase ? 1 : -1) * 2;
  mFontSize=Math.max(MIN_FONTSIZE,Math.min(mFontSize,MAX_FONTSIZE));
  SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);
  prefs.edit().putString(FONTSIZE_KEY,Integer.toString(mFontSize)).apply();
}
