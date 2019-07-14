/** 
 * ??ViewPager,?????ViewPager??????titles????? 
 */
public void setViewPager(ViewPager vp,String[] titles){
  if (vp == null || vp.getAdapter() == null) {
    throw new IllegalStateException("ViewPager or ViewPager adapter can not be NULL !");
  }
  if (titles == null || titles.length == 0) {
    throw new IllegalStateException("Titles can not be EMPTY !");
  }
  if (titles.length != vp.getAdapter().getCount()) {
    throw new IllegalStateException("Titles length must be the same as the page count !");
  }
  this.mViewPager=vp;
  mTitles=new ArrayList<>();
  Collections.addAll(mTitles,titles);
  this.mViewPager.removeOnPageChangeListener(this);
  this.mViewPager.addOnPageChangeListener(this);
  notifyDataSetChanged();
}
