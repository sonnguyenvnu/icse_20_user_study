@Deprecated public static void setupActionBarColor(@NonNull AppCompatActivity activity,int color){
  Toolbar toolbar=(Toolbar)activity.findViewById(R.id.toolbar);
  if (toolbar == null)   return;
  activity.setSupportActionBar(toolbar);
  ActionBar actionBar=activity.getSupportActionBar();
  if (actionBar == null)   return;
  actionBar.setDisplayHomeAsUpEnabled(true);
  ColorDrawable drawable=new ColorDrawable(color);
  actionBar.setBackgroundDrawable(drawable);
  if (SDK_INT >= LOLLIPOP) {
    int darkerColor=ColorUtils.mixColors(color,Color.BLACK,0.75f);
    activity.getWindow().setStatusBarColor(darkerColor);
    toolbar.setElevation(InterfaceUtils.dpToPixels(activity,2));
    View view=activity.findViewById(R.id.toolbarShadow);
    if (view != null)     view.setVisibility(View.GONE);
    view=activity.findViewById(R.id.headerShadow);
    if (view != null)     view.setVisibility(View.GONE);
  }
}
