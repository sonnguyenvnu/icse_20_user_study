public static ComparableResDrawable create(Context context,@DrawableRes int resId){
  Configuration config=new Configuration(context.getResources().getConfiguration());
  Drawable drawable=ContextCompat.getDrawable(context,resId);
  return new ComparableResDrawable(resId,config,drawable);
}
