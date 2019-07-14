@Deprecated public static int getDefaultActionBarColor(Context context){
  if (SDK_INT < LOLLIPOP) {
    return ResourcesCompat.getColor(context.getResources(),R.color.grey_900,context.getTheme());
  }
 else {
    StyledResources res=new StyledResources(context);
    return res.getColor(R.attr.colorPrimary);
  }
}
