/** 
 * ?Toolbar????????
 */
public static void initFullBar(Toolbar toolbar,AppCompatActivity activity){
  ViewGroup.LayoutParams params=toolbar.getLayoutParams();
  params.height=DensityUtil.getStatusHeight(activity) + getSystemActionBarSize(activity);
  toolbar.setLayoutParams(params);
  toolbar.setPadding(toolbar.getLeft(),toolbar.getTop() + DensityUtil.getStatusHeight(activity),toolbar.getRight(),toolbar.getBottom());
  activity.setSupportActionBar(toolbar);
  ActionBar actionBar=activity.getSupportActionBar();
  actionBar.setDisplayHomeAsUpEnabled(true);
  actionBar.setHomeButtonEnabled(true);
}
