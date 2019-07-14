private void loadPrefFragment(PreferenceFragment fragment,@StringRes int titleBarName){
  currentFragment=fragment;
  FragmentTransaction t=getFragmentManager().beginTransaction();
  t.replace(R.id.prefsfragment,fragment);
  t.commit();
  getSupportActionBar().setTitle(titleBarName);
}
