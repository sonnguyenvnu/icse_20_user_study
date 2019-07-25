public void detach(){
  FragmentTransaction ft=mManager.beginTransaction();
  for (  Fragment fragment : mFragments) {
    ft.remove(fragment);
  }
  ft.commit();
  mManager.executePendingTransactions();
  mManager=null;
  mFragments=null;
  mTabLayout.removeOnTabSelectedListener(mListener);
  mListener=null;
  mTabLayout=null;
}
