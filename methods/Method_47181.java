private @ColorInt int correctForIndex(Context c,int color){
  if (color != -1)   return Utils.getColor(c,OLD_SYSTEM_LIST.get(color));
 else   return Utils.getColor(c,R.color.primary_indigo);
}
