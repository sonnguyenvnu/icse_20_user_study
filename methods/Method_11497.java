/** 
 * ??ViewPager,????????????????? 
 */
public void setViewPager(ViewPager vp,String[] titles,FragmentActivity fa,ArrayList<Fragment> fragments){
  if (vp == null) {
    throw new IllegalStateException("ViewPager can not be NULL !");
  }
  if (titles == null || titles.length == 0) {
    throw new IllegalStateException("Titles can not be EMPTY !");
  }
  this.mViewPager=vp;
  this.mViewPager.setAdapter(new InnerPagerAdapter(fa.getSupportFragmentManager(),fragments,titles));
  this.mViewPager.removeOnPageChangeListener(this);
  this.mViewPager.addOnPageChangeListener(this);
  notifyDataSetChanged();
}
