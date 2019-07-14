/** 
 * @deprecated Use {@link #attachToActivity(Fragment)} instead.
 */
public static ScalpelHelperFragment attachTo(FragmentActivity activity){
  FragmentManager fragmentManager=activity.getSupportFragmentManager();
  ScalpelHelperFragment fragment=(ScalpelHelperFragment)fragmentManager.findFragmentByTag(FRAGMENT_TAG);
  if (fragment == null) {
    fragment=new ScalpelHelperFragment();
    fragmentManager.beginTransaction().add(fragment,FRAGMENT_TAG).commit();
  }
  return fragment;
}
